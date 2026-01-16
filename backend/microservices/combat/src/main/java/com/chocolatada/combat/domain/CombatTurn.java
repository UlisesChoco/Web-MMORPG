package com.chocolatada.combat.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CombatTurn {
    private Integer turnNumber;

    private Action playerAction;

    private Action enemyAction;

    private State playerStateAfter;

    private State enemyStateAfter;
}
