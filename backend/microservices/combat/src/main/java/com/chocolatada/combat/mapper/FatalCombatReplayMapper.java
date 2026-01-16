package com.chocolatada.combat.mapper;

import com.chocolatada.combat.dto.FatalCombatReplayDTO;
import com.chocolatada.combat.entity.FatalCombatReplayEntity;

public class FatalCombatReplayMapper {
    public static FatalCombatReplayDTO toFatalCombatReplayDTO(FatalCombatReplayEntity entity) {
        return FatalCombatReplayDTO.builder()
                .playerId(entity.getCombatHistory().getPlayerId())
                .enemyId(entity.getCombatHistory().getEnemyId())
                .date(entity.getCombatHistory().getDate())
                .totalTurns(entity.getCombatHistory().getTotalTurns())
                .turnLog(entity.getTurnLog())
                .build();
    }
}
