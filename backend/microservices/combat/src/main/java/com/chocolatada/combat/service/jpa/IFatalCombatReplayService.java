package com.chocolatada.combat.service.jpa;

import com.chocolatada.combat.dto.FatalCombatReplayDTO;

public interface IFatalCombatReplayService {
    FatalCombatReplayDTO getFatalCombatReplayByCombatHistoryId(Long combatHistoryId);
}
