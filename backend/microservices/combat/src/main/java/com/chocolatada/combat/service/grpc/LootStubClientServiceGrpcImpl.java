package com.chocolatada.combat.service.grpc;

import com.chocolatada.combat.domain.Loot;
import com.chocolatada.combat.grpc.Enemy;
import com.chocolatada.combat.grpc.LootServiceGrpc;
import com.chocolatada.combat.grpc.RollEnemyLootRequest;
import com.chocolatada.combat.grpc.RollEnemyLootResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LootStubClientServiceGrpcImpl {
    private final LootServiceGrpc.LootServiceBlockingStub lootStub;

    public Loot roll(Enemy enemyGrpc) {
        RollEnemyLootRequest request = RollEnemyLootRequest.newBuilder()
                .setEnemyId((int) enemyGrpc.getId())
                .build();

        RollEnemyLootResponse response = lootStub.rollEnemyLoot(request);

        Long itemId = null;

        if(response.getDropped())
            itemId = response.getItemId();

        return Loot.builder()
                .gold(enemyGrpc.getGold())
                .itemId(itemId)
                .build();
    }
}
