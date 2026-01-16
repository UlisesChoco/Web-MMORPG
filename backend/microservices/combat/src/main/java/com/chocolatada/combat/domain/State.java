package com.chocolatada.combat.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class State {
    private Integer hp;

    private Integer stamina;

    private Integer accuracy;

    private Integer evasion;
}
