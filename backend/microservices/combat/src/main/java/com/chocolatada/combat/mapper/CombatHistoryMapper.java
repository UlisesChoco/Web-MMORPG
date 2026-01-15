package com.chocolatada.combat.mapper;

import com.chocolatada.combat.dto.CombatHistoryDTO;
import com.chocolatada.combat.entity.CombatHistoryEntity;

import java.util.ArrayList;
import java.util.List;

public class CombatHistoryMapper {
    public static CombatHistoryDTO toCombatHistoryDTO(CombatHistoryEntity entity) {
        return CombatHistoryDTO.builder()
                .playerId(entity.getPlayerId())
                .enemyId(entity.getEnemyId())
                .totalTurns(entity.getTotalTurns())
                .wasFatal(entity.getWasFatal())
                .date(entity.getDate().toString())
                .build();
    }

    public static List<CombatHistoryDTO> toCombatHistoryDTOs(List<CombatHistoryEntity> entities) {
        List<CombatHistoryDTO> dtos = new ArrayList<>();

        for(CombatHistoryEntity entity : entities)
            dtos.add(toCombatHistoryDTO(entity));

        return dtos;
    }
}
