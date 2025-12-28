package com.chocolatada.tower.mapper;

import com.chocolatada.tower.dto.TowerDTO;
import com.chocolatada.tower.entity.TowerPlayerProgressEntity;

public class TowerPlayerProgressMapper {
    public static TowerDTO toTowerDTO(TowerPlayerProgressEntity entity) {
        return TowerDTO.builder()
                .floor(entity.getTower().getFloor())
                .levelRange(entity.getTower().getLevelRange())
                .build();
    }
}
