package com.example.demo.service.jpa;

import com.example.demo.dto.BonusStats;
import com.example.demo.dto.PlayerStats;
import com.example.demo.exception.InvalidPlayerClassDataException;

public interface IPlayerClassStatsService {
    PlayerStats getScaledPlayerClassStats(Long classId, BonusStats bonus, int level) throws InvalidPlayerClassDataException;
}
