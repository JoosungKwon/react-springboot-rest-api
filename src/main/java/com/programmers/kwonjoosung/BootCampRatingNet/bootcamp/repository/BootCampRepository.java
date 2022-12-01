package com.programmers.kwonjoosung.BootCampRatingNet.bootcamp.repository;

import com.programmers.kwonjoosung.BootCampRatingNet.bootcamp.entity.BootCamp;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BootCampRepository {

    BootCamp save(BootCamp bootCamp);

    Optional<BootCamp> findByCampId(UUID campId);

    Optional<BootCamp> findByCampName(String campName);

    List<BootCamp> findAll();

    void deleteByCampId(UUID campId);
}
