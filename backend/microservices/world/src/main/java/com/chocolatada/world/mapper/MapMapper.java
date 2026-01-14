package com.chocolatada.world.mapper;

import com.chocolatada.world.dto.EnemyDTO;
import com.chocolatada.world.dto.MapDTO;
import com.chocolatada.world.dto.MapEnemyDTO;
import com.chocolatada.world.entity.EnemyEntity;
import com.chocolatada.world.entity.MapEntity;

import java.util.ArrayList;
import java.util.List;

public class MapMapper {

    public static MapDTO toDTO(MapEntity entity) {
        return MapDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .rangeLevel(entity.getRangeLevel())
                .build();
    }

    public static List<MapDTO> toDTOs(List<MapEntity> entities) {
        List<MapDTO> dtos = new ArrayList<>();
        for (MapEntity entity : entities)
            dtos.add(toDTO(entity));
        return dtos;
    }

    public static MapEnemyDTO toMapEnemyDTO(EnemyEntity entity) {
        return MapEnemyDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .gold(entity.getGold())
                .build();
    }

    public static List<MapEnemyDTO> toMapEnemyDTOs(List<EnemyEntity> entities) {
        if (entities == null)
            return new ArrayList<>();

        List<MapEnemyDTO> dtos = new ArrayList<>();
        for (EnemyEntity entity : entities)
            dtos.add(toMapEnemyDTO(entity));
        return dtos;
    }

    public static EnemyDTO toEnemyDTO(EnemyEntity entity) {
        return EnemyDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .type(entity.getType())
                .level(entity.getLevel())
                .experience(entity.getExperience())
                .gold(entity.getGold())
                .critRate(entity.getCritRate())
                .critDamage(entity.getCritDamage())
                .atk(entity.getAtk())
                .def(entity.getDef())
                .stamina(entity.getStamina())
                .accuracy(entity.getAccuracy())
                .evasion(entity.getEvasion())
                .build();
    }

    public static List<EnemyDTO> toEnemyDTOs(List<EnemyEntity> entities) {
        if (entities == null)
            return new ArrayList<>();

        List<EnemyDTO> dtos = new ArrayList<>();
        for (EnemyEntity entity : entities)
            dtos.add(toEnemyDTO(entity));
        return dtos;
    }
}

