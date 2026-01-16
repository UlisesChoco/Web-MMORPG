package com.chocolatada.combat.service.domain;

import com.chocolatada.combat.domain.Combat;
import io.grpc.StatusRuntimeException;

public interface ICombatService {
    Combat processCombat(Long playerId, Long enemyId) throws StatusRuntimeException;
}
