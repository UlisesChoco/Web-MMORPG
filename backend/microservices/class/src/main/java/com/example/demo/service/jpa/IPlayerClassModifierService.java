package com.example.demo.service.jpa;

import com.example.demo.entity.PlayerClassModifier;
import com.example.demo.exception.InvalidPlayerClassDataException;

public interface IPlayerClassModifierService {
    void saveAllFromResources() throws InvalidPlayerClassDataException;
    PlayerClassModifier findById(Long id) throws InvalidPlayerClassDataException;
    PlayerClassModifier findByPlayerClassId(Long playerClassId) throws InvalidPlayerClassDataException;
}
