package com.ozdeniz.fittrack.controller;

import com.ozdeniz.fittrack.api.TrainingApi;
import com.ozdeniz.fittrack.dto.TrainingCreateDto;
import com.ozdeniz.fittrack.dto.TrainingDto;
import com.ozdeniz.fittrack.dto.TrainingUpdateDto;
import com.ozdeniz.fittrack.service.TrainingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class TrainingController implements TrainingApi {

    private final TrainingService service;

    @Override
    public ResponseEntity<UUID> createTraining(TrainingCreateDto trainingCreateDto, MultipartFile image) {
        return ResponseEntity.ok(service.createTraining(trainingCreateDto, image));
    }

    @Override
    public ResponseEntity<TrainingDto> getTraining(UUID id) {
        return ResponseEntity.ok(service.getTraining(id));
    }

    @Override
    public ResponseEntity<List<TrainingDto>> getAllTrainings() {
        return ResponseEntity.ok(service.getAllTrainings());
    }

    @Override
    public ResponseEntity<TrainingDto> updateTraining(UUID id, TrainingUpdateDto trainingUpdateDto, MultipartFile image) {
        return ResponseEntity.ok(service.updateTraining(id, trainingUpdateDto, image));
    }

    @Override
    public ResponseEntity<Boolean> deleteTraining(UUID id) {
        return ResponseEntity.ok(service.deleteTraining(id));
    }
}
