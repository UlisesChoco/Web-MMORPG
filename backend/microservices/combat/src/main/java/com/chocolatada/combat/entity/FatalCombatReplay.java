package com.chocolatada.combat.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "combat_history")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FatalCombatReplay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "combat_history_id", nullable = false)
    private CombatHistoryEntity combatHistory;

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String turnLog;
}
