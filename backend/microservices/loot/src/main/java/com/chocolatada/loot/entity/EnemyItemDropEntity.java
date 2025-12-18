package com.chocolatada.loot.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "enemy_item_drop")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnemyItemDropEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long enemyId;

    @Column(nullable = false)
    private Long itemId;

    @Column(nullable = false)
    private Float probability;
}
