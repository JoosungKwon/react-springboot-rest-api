package com.programmers.kwonjoosung.BootCampRatingNet.bootcamp.service;

import com.programmers.kwonjoosung.BootCampRatingNet.bootcamp.dto.BootCampResponseDto;
import com.programmers.kwonjoosung.BootCampRatingNet.bootcamp.dto.CreateBootCampRequest;
import com.programmers.kwonjoosung.BootCampRatingNet.bootcamp.repository.BootCampRepository;
import com.programmers.kwonjoosung.BootCampRatingNet.bootcamp.utils.BootCampConverter;
import com.programmers.kwonjoosung.BootCampRatingNet.exception.DataNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.programmers.kwonjoosung.BootCampRatingNet.bootcamp.utils.BootCampConverter.toBootCamp;
import static com.programmers.kwonjoosung.BootCampRatingNet.bootcamp.utils.BootCampConverter.toResponse;

@Service
public class BootCampServiceImpl implements BootCampService {

    private final BootCampRepository bootCampRepository;

    public BootCampServiceImpl(BootCampRepository bootCampRepository) {
        this.bootCampRepository = bootCampRepository;
    }

    @Override
    public BootCampResponseDto save(CreateBootCampRequest request) {
        return toResponse(bootCampRepository.save(toBootCamp(request)));
    }

    @Override
    public BootCampResponseDto findByCampName(String campName) {
        return toResponse(bootCampRepository.findByCampName(campName)
                .orElseThrow(() -> new DataNotFoundException("bootcamp", "camp_name", campName)));
    }

    @Override
    public List<BootCampResponseDto> getAllBootCamp() {
        return bootCampRepository.findAll()
                .stream()
                .map(BootCampConverter::toResponse)
                .toList();
    }

    @Override
    public void deleteByCampId(UUID campId) {
        bootCampRepository.deleteByCampId(campId);
    }
}
