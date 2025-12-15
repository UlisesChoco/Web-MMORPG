package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.PlayerClass;

@Repository
public interface PlayerClassRepository extends JpaRepository<PlayerClass, Long> {
    Optional<PlayerClass> findByName(String name);
}
