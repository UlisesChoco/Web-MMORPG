package com.chocolatada.world.mapper;

import com.chocolatada.world.dto.NPCItemDTO;
import com.chocolatada.world.dto.NPCDTO;
import com.chocolatada.world.entity.NPCEntity;
import com.chocolatada.world.entity.NPCItemEntity;

import java.util.ArrayList;
import java.util.List;

public class NPCMapper {

    public static NPCDTO toDTO(NPCEntity entity) {
        return NPCDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .type(entity.getType())
                .items(toItemDTOs(entity.getItems()))
                .build();
    }

    public static List<NPCDTO> toDTOs(List<NPCEntity> entities) {
        List<NPCDTO> dtos = new ArrayList<>();
        for (NPCEntity entity : entities)
            dtos.add(toDTO(entity));
        return dtos;
    }

    public static NPCItemDTO toItemDTO(NPCItemEntity entity) {
        return NPCItemDTO.builder()
                .id(entity.getId())
                .itemId(entity.getItemId())
                .price(entity.getPrice())
                .build();
    }

    public static List<NPCItemDTO> toItemDTOs(List<NPCItemEntity> entities) {
        if(entities == null)
            return new ArrayList<>();

        List<NPCItemDTO> dtos = new ArrayList<>();
        for (NPCItemEntity entity : entities)
            dtos.add(toItemDTO(entity));
        return dtos;
    }
}

