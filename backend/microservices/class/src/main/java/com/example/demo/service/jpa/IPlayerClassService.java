package com.example.demo.service.jpa;

import java.util.List;

import com.example.demo.entity.PlayerClass;
import com.example.demo.exception.InvalidPlayerClassDataException;

public interface IPlayerClassService {
    PlayerClass findById(Long id) throws InvalidPlayerClassDataException;

    PlayerClass findByName(String name) throws InvalidPlayerClassDataException;

    List<PlayerClass> findAll();

    PlayerClass save(PlayerClass playerClass) throws InvalidPlayerClassDataException;

    void saveAllFromResources() throws InvalidPlayerClassDataException;
}
