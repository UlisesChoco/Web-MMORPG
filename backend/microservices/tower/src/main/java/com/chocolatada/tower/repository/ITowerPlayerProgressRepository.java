package com.chocolatada.tower.repository;

import com.chocolatada.tower.entity.TowerPlayerProgressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITowerPlayerProgressRepository extends JpaRepository<TowerPlayerProgressEntity, Long> {
}
