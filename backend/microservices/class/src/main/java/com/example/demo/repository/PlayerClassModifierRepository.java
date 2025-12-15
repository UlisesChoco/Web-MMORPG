package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.PlayerClassModifier;

@Repository
public interface PlayerClassModifierRepository extends JpaRepository<PlayerClassModifier, Long> {
    Optional<PlayerClassModifier> findByPlayerClassId(Long playerClassId);
}
