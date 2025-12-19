package com.chocolatada.tower.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tower_player_progress")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TowerPlayerProgressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tower_id", nullable = false)
    private Long towerId;

    @Column(nullable = false)
    private Long playerId;
}
