package com.programmers.kwonjoosung.BootCampRatingNet.bootcamp.controller;

import com.programmers.kwonjoosung.BootCampRatingNet.bootcamp.dto.BootCampInfoResponse;
import com.programmers.kwonjoosung.BootCampRatingNet.bootcamp.dto.BootCampWithReviewResponse;
import com.programmers.kwonjoosung.BootCampRatingNet.bootcamp.dto.CreateBootCampRequest;
import com.programmers.kwonjoosung.BootCampRatingNet.bootcamp.service.BootCampService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/bootcamps",produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
@RestController
public class BootCampRestController {

    private final BootCampService bootCampService;

    @PostMapping()
    public ResponseEntity<BootCampInfoResponse> createBootCamp(@RequestBody CreateBootCampRequest request) {
        return ResponseEntity.ok(bootCampService.createBootCamp(request));
    }

    @GetMapping("/{campId}")
    public ResponseEntity<BootCampWithReviewResponse> findByCampId(@PathVariable UUID campId) {
        return ResponseEntity.ok(bootCampService.findBootCampByCampId(campId));
    }

    @GetMapping()
    public ResponseEntity<List<BootCampInfoResponse>> getAllBootCamp() {
        return ResponseEntity.ok(bootCampService.getAllBootCamp());
    }

    @DeleteMapping("/{campId}")
    public ResponseEntity<Void> deleteByCampId(@PathVariable UUID campId) {
        bootCampService.deleteBootCampByCampId(campId);
        return ResponseEntity.ok().build();
    }
}

