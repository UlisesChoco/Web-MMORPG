package com.chocolatada.world.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "enemy")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnemyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnemyType type;

    @Column(nullable = false)
    private Integer level;

    @Column(nullable = false)
    private Integer experience;

    @Column(nullable = false)
    private Integer gold;

    @Column(name = "crit_rate", nullable = false)
    private Float critRate;

    @Column(name = "crit_damage", nullable = false)
    private Float critDamage;

    @Column(nullable = false)
    private Integer hp;

    @Column(nullable = false)
    private Integer atk;

    @Column(name = "def", nullable = false)
    private Integer def;

    @Column(nullable = false)
    private Integer stamina;

    @Column(nullable = false)
    private Integer accuracy;

    @Column(nullable = false)
    private Integer evasion;

    @ManyToMany
    @JoinTable(
            name = "enemy_map",
            joinColumns = @JoinColumn(name = "enemy_id"),
            inverseJoinColumns = @JoinColumn(name = "map_id")
    )
    private List<MapEntity> maps;
}

