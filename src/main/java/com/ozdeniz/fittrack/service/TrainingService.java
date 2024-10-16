package com.ozdeniz.fittrack.service;

import com.ozdeniz.fittrack.dto.TrainingCreateDto;
import com.ozdeniz.fittrack.dto.TrainingDto;
import com.ozdeniz.fittrack.dto.TrainingUpdateDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface TrainingService {

    UUID createTraining(TrainingCreateDto trainingCreateDto, MultipartFile image);
    TrainingDto getTraining(UUID id);
    List<TrainingDto> getAllTrainings();
    TrainingDto updateTraining(UUID id, TrainingUpdateDto trainingUpdateDto, MultipartFile image);
    Boolean deleteTraining(UUID id);

}
