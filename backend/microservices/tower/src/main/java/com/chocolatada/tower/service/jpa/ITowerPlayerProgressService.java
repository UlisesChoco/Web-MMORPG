package com.chocolatada.tower.service.jpa;

import com.chocolatada.tower.dto.TowerDTO;
import com.chocolatada.tower.entity.TowerEntity;
import com.chocolatada.tower.exception.InvalidTowerDataException;

public interface ITowerPlayerProgressService {
    TowerDTO getMaxFloorReached(Long playerId) throws InvalidTowerDataException;
    boolean registerPlayerProgress(TowerEntity towerEntity, Long playerId) throws InvalidTowerDataException;
}
