package com.chocolatada.tower.repository;

import com.chocolatada.tower.entity.TowerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITowerRepository extends JpaRepository<TowerEntity, Long> {
    List<TowerEntity> findAllByOrderByFloorAsc();
}
