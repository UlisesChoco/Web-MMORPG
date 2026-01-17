package com.chocolatada.combat.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "combat_history")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CombatHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long playerId;

    @Column(nullable = false)
    private Long enemyId;

    @Column(nullable = false)
    private Integer totalTurns;

    @Column(nullable = false)
    private Boolean wasFatal;

    @Column(nullable = false)
    private LocalDate date;
}
