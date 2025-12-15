package com.example.demo.service.jpa.impl;

import org.springframework.stereotype.Service;

import com.example.demo.configuration.resources.definition.player_class_modifier.ClassModifierConfigurationProperties;
import com.example.demo.configuration.resources.definition.player_class_modifier.ClassModifierDefinition;
import com.example.demo.entity.PlayerClass;
import com.example.demo.entity.PlayerClassModifier;
import com.example.demo.exception.InvalidPlayerClassDataException;
import com.example.demo.mapper.PlayerClassModifierMapper;
import com.example.demo.repository.PlayerClassModifierRepository;
import com.example.demo.service.jpa.IPlayerClassModifierService;
import com.example.demo.service.jpa.IPlayerClassService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlayerClassModifierServiceImpl implements IPlayerClassModifierService {
    private final PlayerClassModifierRepository playerClassModifierRepository;

    private final ClassModifierConfigurationProperties classModifierConfigurationProperties;

    private final IPlayerClassService playerClassService;

    @Override
    public void saveAllFromResources() throws InvalidPlayerClassDataException {
        for(ClassModifierDefinition definition : classModifierConfigurationProperties.getModifiers()) {
            String className = definition.getName();

            PlayerClass playerClass = null;
            try {
                playerClass = playerClassService.findByName(className);
            } catch (InvalidPlayerClassDataException e) {
                throw new InvalidPlayerClassDataException(
                    "No se puede crear el modificador de clase de jugador. La clase de jugador con nombre: "
                    + className + " no existe."
                );
            }
            
            PlayerClassModifier classModifier = PlayerClassModifierMapper.toPlayerClassModifier(definition);

            classModifier.setPlayerClass(playerClass);

            playerClassModifierRepository.save(classModifier);
        }
    }

    @Override
    public PlayerClassModifier findById(Long id) throws InvalidPlayerClassDataException {
        return playerClassModifierRepository.findById(id).orElseThrow(() -> 
            new InvalidPlayerClassDataException("Modificador de clase de jugador no encontrado con ID: " + id)
        );
    }

    @Override
    public PlayerClassModifier findByPlayerClassId(Long playerClassId) throws InvalidPlayerClassDataException {
        return playerClassModifierRepository.findByPlayerClassId(playerClassId).orElseThrow(() -> 
            new InvalidPlayerClassDataException("Modificador de clase de jugador no encontrado para la clase de jugador con ID: " + playerClassId)
        );
    }
}
