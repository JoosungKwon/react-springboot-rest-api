package com.programmers.kwonjoosung.BootCampRatingNet.bootcamp.repository;

import com.programmers.kwonjoosung.BootCampRatingNet.bootcamp.entity.BootCamp;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BootCampRepository {

    BootCamp save(BootCamp bootCamp);

    Optional<BootCamp> findByCampIdWithReviews(UUID campId);

    List<BootCamp> findAll();

    void deleteByCampId(UUID campId);
}
