package com.chocolatada.combat.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Combat {
    private Long combatHistoryId;

    private Boolean wasFatal;

    private Integer totalTurns;

    private List<CombatTurn> turns;

    private Loot loot;
}
