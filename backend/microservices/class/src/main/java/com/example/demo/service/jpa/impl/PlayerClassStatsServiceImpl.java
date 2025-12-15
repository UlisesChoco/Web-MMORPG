package com.example.demo.service.jpa.impl;

import com.example.demo.dto.BonusStats;
import com.example.demo.dto.PlayerStats;
import org.springframework.stereotype.Service;

import com.example.demo.entity.PlayerClass;
import com.example.demo.entity.PlayerClassModifier;
import com.example.demo.exception.InvalidPlayerClassDataException;
import com.example.demo.service.formula.ScalateStatFormula;
import com.example.demo.service.jpa.IPlayerClassModifierService;
import com.example.demo.service.jpa.IPlayerClassService;
import com.example.demo.service.jpa.IPlayerClassStatsService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlayerClassStatsServiceImpl implements IPlayerClassStatsService {

    private final IPlayerClassService playerClassService;
    private final IPlayerClassModifierService playerClassModifierService;

    @Override
    public PlayerStats getScaledPlayerClassStats(Long classId, BonusStats bonus, int level) throws InvalidPlayerClassDataException {
        PlayerClass playerClass = playerClassService.findById(classId);

        PlayerClassModifier classModifier = playerClassModifierService.findByPlayerClassId(classId);

        float critRate = ScalateStatFormula.calculateStatAtLevel(
            playerClass.getCritRate(), 
            classModifier.getCritRateModifier(),
            0,
            level
        );
        float critDamage = ScalateStatFormula.calculateStatAtLevel(
            playerClass.getCritDamage(), 
            classModifier.getCritDamageModifier(),
            0,
            level
        );
        int hp = ScalateStatFormula.calculateStatAtLevel(
            playerClass.getHp(), 
            classModifier.getHpModifier(),
            bonus.getHp(),
            level
        );
        int atk = ScalateStatFormula.calculateStatAtLevel(
            playerClass.getAtk(), 
            classModifier.getAtkModifier(),
            bonus.getAtk(),
            level
        );
        int def = ScalateStatFormula.calculateStatAtLevel(
            playerClass.getDef(), 
            classModifier.getDefModifier(),
            bonus.getDef(),
            level
        );
        int stamina = ScalateStatFormula.calculateStatAtLevel(
            playerClass.getStamina(), 
            classModifier.getStaminaModifier(),
            bonus.getStamina(),
            level
        );
        int accuracy = ScalateStatFormula.calculateStatAtLevel(
            playerClass.getAccuracy(), 
            classModifier.getAccuracyModifier(),
            bonus.getAccuracy(),
            level
        );
        int evasion = ScalateStatFormula.calculateStatAtLevel(
            playerClass.getEvasion(), 
            classModifier.getEvasionModifier(),
            bonus.getEvasion(),
            level
        );

        return PlayerStats.builder()
            .critRate(critRate)
            .critDamage(critDamage)
            .hp(hp)
            .atk(atk)
            .def(def)
            .stamina(stamina)
            .accuracy(accuracy)
            .evasion(evasion)
            .build();
    }
}
