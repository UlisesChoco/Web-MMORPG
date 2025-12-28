package com.chocolatada.tower.mapper;

import com.chocolatada.tower.dto.TowerEnemyDTO;
import com.chocolatada.tower.entity.TowerEnemyEntity;

import java.util.ArrayList;
import java.util.List;

public class TowerEnemyMapper {
    public static TowerEnemyDTO toTowerEnemyDTO(TowerEnemyEntity entity) {
        return TowerEnemyDTO.builder()
                .enemyId(entity.getId())
                .build();
    }

    public static List<TowerEnemyDTO> toTowerEnemyDTOs(List<TowerEnemyEntity> entities) {
        List<TowerEnemyDTO> dtos = new ArrayList<>();

        for(TowerEnemyEntity entity : entities)
            dtos.add(toTowerEnemyDTO(entity));

        return dtos;
    }
}
