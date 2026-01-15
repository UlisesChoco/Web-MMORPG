package com.chocolatada.combat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CombatHistoryDTO {
    private Long playerId;

    private Long enemyId;

    private Integer totalTurns;

    private Boolean wasFatal;

    private String date;
}
