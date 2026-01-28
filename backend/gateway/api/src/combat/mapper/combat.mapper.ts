import { Long } from "@grpc/proto-loader";
import { ProcessCombatResponseDTO } from "../dto/combat/process-combat-response.dto";
import { TurnActionGrpc, TurnResultGrpc, type ProcessCombatResponse } from "../interfaces/combat/response/process-combat-response.interface";

export class CombatMapper {
    static mapProcessCombatResponse(data: any): ProcessCombatResponseDTO {
        const response = data as ProcessCombatResponse;

        const turnsMapped = response.turns.map(turn => {
            return {
                turnNumber: turn.turnNumber,
                playerAction: {
                    turnAction: TurnActionGrpc[turn.playerAction.turnAction],
                    turnResult: TurnResultGrpc[turn.playerAction.turnResult],
                    damage: turn.playerAction.damage?.toNumber() ?? 0,
                    critical: turn.playerAction.critical ?? false,
                },
                enemyAction: {
                    turnAction: TurnActionGrpc[turn.enemyAction.turnAction],
                    turnResult: TurnResultGrpc[turn.enemyAction.turnResult],
                    damage: turn.enemyAction.damage?.toNumber() ?? 0,
                    critical: turn.enemyAction.critical ?? false,
                },
                playerStateAfter: {
                    hp: turn.playerStateAfter.hp?.toNumber() ?? 0,
                    stamina: turn.playerStateAfter.stamina?.toNumber() ?? 0,
                    accuracy: turn.playerStateAfter.accuracy?.toNumber() ?? 0,
                    evasion: turn.playerStateAfter.evasion?.toNumber() ?? 0,
                },
                enemyStateAfter: {
                    hp: turn.enemyStateAfter.hp?.toNumber() ?? 0,
                    stamina: turn.enemyStateAfter.stamina?.toNumber() ?? 0,
                    accuracy: turn.enemyStateAfter.accuracy?.toNumber() ?? 0,
                    evasion: turn.enemyStateAfter.evasion?.toNumber() ?? 0,
                },
            };
        });

        const mapped: ProcessCombatResponseDTO = {
            combatId: response.combatId?.toNumber() ?? 0,
            wasFatal: response.wasFatal,
            totalTurns: response.totalTurns,
            turns: turnsMapped,
            loot: {
                gold: response.loot.gold ?? 0,
                itemId: response.loot.itemId?.toNumber() ?? undefined,
            },
        };

        return mapped;
    }
}