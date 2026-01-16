package com.chocolatada.combat.service.domain.impl;

import com.chocolatada.combat.constant.TurnAction;
import com.chocolatada.combat.constant.TurnResult;
import com.chocolatada.combat.domain.*;
import com.chocolatada.combat.grpc.*;
import com.chocolatada.combat.mapper.EntityMapper;
import com.chocolatada.combat.mapper.StateMapper;
import com.chocolatada.combat.service.domain.ICombatService;
import com.chocolatada.combat.service.domain.formula.Formula;
import io.grpc.StatusRuntimeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

//esto lo tengo que refactorizar mas adelante
//este service hace dos cosas; logica de combate y comunicacion grpc
//deberia separar la comunicacion grpc a la capa superior grpc que use este service
@Service
@RequiredArgsConstructor
public class CombatServiceImpl implements ICombatService {
    private final EnemyServiceGrpc.EnemyServiceBlockingStub enemyStub;

    private final PlayerServiceGrpc.PlayerServiceBlockingStub playerStub;

    private final PlayerClassServiceGrpc.PlayerClassServiceBlockingStub playerClassStub;

    private final LootServiceGrpc.LootServiceBlockingStub lootStub;

    private final Random random;

    @Override
    public Combat processCombat(Long playerId, Long enemyId) throws StatusRuntimeException {
        Enemy enemyGrpc = getEnemy(enemyId);

        Combat combat = new Combat();
        Entity player = getPlayer(playerId);
        Entity enemy = EntityMapper.toEntity(enemyGrpc);

        int turnCounter = 1;
        while(noOneWins(player, enemy)) {
            Action playerAction = processTurn(player, enemy);
            Action enemyAction = processTurn(enemy, player);

            updateEntityAfterTurn(player, enemyAction);
            updateEntityAfterTurn(enemy, playerAction);

            State playerStateAfter = StateMapper.toState(player);
            State enemyStateAfter = StateMapper.toState(enemy);

            CombatTurn combatTurn = CombatTurn.builder()
                    .turnNumber(turnCounter)
                    .playerAction(playerAction)
                    .enemyAction(enemyAction)
                    .playerStateAfter(playerStateAfter)
                    .enemyStateAfter(enemyStateAfter)
                    .build();

            combat.getTurns().add(combatTurn);

            turnCounter++;
        }

        combat.setTotalTurns(turnCounter - 1);

        if(playerWon(enemy)) {
            combat.setWasFatal(false);

            RollEnemyLootRequest request = RollEnemyLootRequest.newBuilder()
                    .setEnemyId((int) enemyGrpc.getId())
                    .build();

            RollEnemyLootResponse response = lootStub.rollEnemyLoot(request);

            Long itemId = null;

            if(response.getDropped())
                itemId = response.getItemId();

            Loot loot = Loot.builder()
                    .gold(enemyGrpc.getGold())
                    .itemId(itemId)
                    .build();

            combat.setLoot(loot);
        }

        if(enemyWon(player)) {
            combat.setWasFatal(true);
            combat.setLoot(null);
            MarkPlayerAsDeadRequest request = MarkPlayerAsDeadRequest.newBuilder()
                    .setPlayerId(playerId)
                    .build();
            playerStub.markPlayerAsDead(request);
        }

        return combat;
    }

    private Player getPlayerResponse(Long playerId) throws StatusRuntimeException {
        GetPlayerByIdRequest request = com.chocolatada.combat.grpc.GetPlayerByIdRequest.newBuilder()
                .setPlayerId(playerId)
                .build();

        return playerStub.getPlayerById(request).getPlayer();
    }

    private Entity getPlayer(Long playerId) throws StatusRuntimeException {
        Player player = getPlayerResponse(playerId);
        System.out.println("PLAYER: "+player);

        BonusStats bonusStats = BonusStats.newBuilder()
                .setHp(player.getHpBonus())
                .setAtk(player.getAtkBonus())
                .setDef(player.getDefBonus())
                .setStamina(player.getStaminaBonus())
                .setAccuracy(player.getAccuracyBonus())
                .setEvasion(player.getEvasionBonus())
                .build();

        GetScaledClassStatsRequest request = GetScaledClassStatsRequest.newBuilder()
                .setClassId(player.getClassId())
                .setBonus(bonusStats)
                .setLevel(player.getLevel())
                .build();

        System.out.println("REQUEST: "+request);

        GetScaledClassStatsResponse response = playerClassStub.getScaledClassStats(request);
        ScaledStats scaledStats = response.getScaledStats();

        return EntityMapper.toEntity(scaledStats);
    }

    private Enemy getEnemy(Long enemyId) throws StatusRuntimeException {
        GetEnemyByIdRequest request = GetEnemyByIdRequest.newBuilder()
                .setEnemyId(enemyId)
                .build();

        return enemyStub.getEnemyById(request).getEnemy();
    }


    private boolean noOneWins(Entity player, Entity enemy) {
        return player.getHp() > 0 && enemy.getHp() > 0;
    }

    private Action processTurn(Entity source, Entity target) {
        float hitChance = Formula.calculateHitChance(source, target);
        float roll = random.nextFloat();

        boolean hit = roll < hitChance;
        boolean critical = false;
        int damage = 0;
        TurnResult turnResult = TurnResult.MISS;

        if(hit) {
            damage = Formula.calculateDamage(source, target);
            turnResult = TurnResult.HIT;

            float critChance = Formula.calculateEffectiveCritRate(source);
            float critRoll = random.nextFloat();

            critical = critRoll < critChance;

            if(critical) {
                float critMultiplier = Formula.calculateEffectiveCritDamage(source);
                damage = Math.round(damage * critMultiplier);
                turnResult = TurnResult.CRITICAL_HIT;
            }
        }

        source.setStamina(source.getStamina() - Formula.calculateStaminaCost(source));

        return Action.builder()
                .turnAction(TurnAction.BASIC_ATTACK)
                .turnResult(turnResult)
                .damage(damage)
                .critical(critical)
                .build();
    }

    public static void updateEntityAfterTurn(Entity entity, Action action) {
        if(action.getTurnResult().equals(TurnResult.MISS))
            return;

        int newHp = entity.getHp() - action.getDamage();
        entity.setHp(Math.max(newHp, 0));
    }

    private boolean playerWon(Entity enemy) {
        return enemy.getHp() <= 0;
    }

    private boolean enemyWon(Entity player) {
        return player.getHp() <= 0;
    }
}
