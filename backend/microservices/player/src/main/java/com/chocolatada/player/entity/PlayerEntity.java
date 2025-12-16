package com.chocolatada.player.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "player_class")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlayerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long classId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Boolean alive;

    @Column(nullable = false)
    private int gold;

    @Column(nullable = false)
    private int level;

    @Column(nullable = false)
    private int experience;

    @Column(nullable = false)
    private int experienceLimit;

    @Column(nullable = false)
    private int freeStatPoints;

    @Column(nullable = false)
    private int hpBonus;

    @Column(nullable = false)
    private int atkBonus;

    @Column(nullable = false)
    private int defBonus;

    @Column(nullable = false)
    private int staminaBonus;

    @Column(nullable = false)
    private int accuracyBonus;

    @Column(nullable = false)
    private int evasionBonus;
}
