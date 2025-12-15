package com.example.demo.mapper;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.configuration.resources.definition.player_class.ClassDefinition;
import com.example.demo.entity.PlayerClass;
import com.example.demo.grpc.ClassData;

public class PlayerClassMapper {
    public static PlayerClass toPlayerClass(ClassDefinition classDef) {
        PlayerClass playerClass = PlayerClass.builder()
                .name(classDef.getName())
                .description(classDef.getDescription())
                .critRate(classDef.getCritRate())
                .critDamage(classDef.getCritDamage())
                .hp(classDef.getHp())
                .atk(classDef.getAtk())
                .def(classDef.getDef())
                .stamina(classDef.getStamina())
                .accuracy(classDef.getAccuracy())
                .evasion(classDef.getEvasion())
                .build();

        return playerClass;
    }

    public static List<PlayerClass> toPlayerClasses(List<ClassDefinition> classDefs) {
        List<PlayerClass> playerClasses = new ArrayList<>();

        for(ClassDefinition classDef : classDefs)
            playerClasses.add(toPlayerClass(classDef));

        return playerClasses;
    }

    public static ClassData toClassData(PlayerClass playerClass) {
        ClassData classData = ClassData.newBuilder()
                .setId(playerClass.getId())
                .setName(playerClass.getName())
                .setDescription(playerClass.getDescription())
                .setCritRate(playerClass.getCritRate())
                .setCritDamage(playerClass.getCritDamage())
                .setHp(playerClass.getHp())
                .setAtk(playerClass.getAtk())
                .setDef(playerClass.getDef())
                .setStamina(playerClass.getStamina())
                .setAccuracy(playerClass.getAccuracy())
                .setEvasion(playerClass.getEvasion())
                .build();

        return classData;
    }

    public static List<ClassData> toClassesData(List<PlayerClass> playerClasses) {
        List<ClassData> classData = new ArrayList<>();

        for(PlayerClass playerClass : playerClasses)
            classData.add(toClassData(playerClass));

        return classData;
    }
}
