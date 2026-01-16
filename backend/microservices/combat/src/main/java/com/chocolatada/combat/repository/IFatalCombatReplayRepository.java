package com.chocolatada.combat.repository;

import com.chocolatada.combat.entity.FatalCombatReplayEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IFatalCombatReplayRepository extends JpaRepository<FatalCombatReplayEntity, Long> {
    Optional<FatalCombatReplayEntity> findByCombatHistoryId(Long combatHistoryId);
}
