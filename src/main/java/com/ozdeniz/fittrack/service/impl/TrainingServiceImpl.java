package com.ozdeniz.fittrack.service.impl;

import com.ozdeniz.fittrack.dto.TrainingCreateDto;
import com.ozdeniz.fittrack.dto.TrainingDto;
import com.ozdeniz.fittrack.dto.TrainingUpdateDto;
import com.ozdeniz.fittrack.exception.ErrorMessages;
import com.ozdeniz.fittrack.exception.FittrackException;
import com.ozdeniz.fittrack.model.Category;
import com.ozdeniz.fittrack.model.Difficulty;
import com.ozdeniz.fittrack.model.Training;
import com.ozdeniz.fittrack.repository.CategoryRepository;
import com.ozdeniz.fittrack.repository.DifficultyRepository;
import com.ozdeniz.fittrack.repository.TrainingRepository;
import com.ozdeniz.fittrack.service.AssetService;
import com.ozdeniz.fittrack.service.AuthService;
import com.ozdeniz.fittrack.service.TrainingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RequiredArgsConstructor
@Service
public class TrainingServiceImpl implements TrainingService {

    private final TrainingRepository repository;
    private final AssetService assetService;
    private final CategoryRepository categoryRepository;
    private final DifficultyRepository difficultyRepository;
    private final AuthService authService;

    @Override
    public UUID createTraining(TrainingCreateDto trainingCreateDto, MultipartFile image) {
        Training training = new Training();

        BeanUtils.copyProperties(trainingCreateDto, training);
        String imageName = assetService.uploadImage(image);
        training.setImageName(imageName);

        return repository.save(training).getId();
    }

    public TrainingDto convertTrainingData(Training training) {
        String difficulty = "";
        Optional<Difficulty> existDifficulty = difficultyRepository.findById(training.getDifficulty());
        if (existDifficulty.isPresent()) {
            difficulty = existDifficulty.get().getName();
        }

        String categoryName = "";
        Optional<Category> existCategory = categoryRepository.findById(training.getCategory());
        if (existCategory.isPresent()) {
            categoryName = existCategory.get().getName();
        }

        byte[] image = new byte[0];
        if(training.getImageName() != null && !training.getImageName().startsWith("https://")) {
            image = assetService.getImage(training.getImageName());
        }

        return new TrainingDto(
                training.getId(),
                training.getUserId(),
                training.getTitle(),
                training.getDescription(),
                training.getDuration(),
                difficulty,
                categoryName,
                image,
                training.getCreatedDate(),
                training.getUpdatedDate()
        );
    }

    @Override
    public TrainingDto getTraining(UUID id) {
        Optional<Training> existTraining = repository.findById(id);

        if (existTraining.isEmpty()) {
            throw FittrackException.withStatusAndMessage(HttpStatus.NOT_FOUND, ErrorMessages.TRAINING_NOT_FOUND);
        }

        Training training = existTraining.get();

        return convertTrainingData(training);
    }

    @Override
    public List<TrainingDto> getAllTrainings() {
        List<Training> existTrainings = repository.findAll();
        existTrainings.sort(Comparator.comparing(Training::getCreatedDate).reversed());

        List<TrainingDto> trainingDtos = new ArrayList<>();

        for (Training training : existTrainings) {
            TrainingDto trainingDto = convertTrainingData(training);
            trainingDtos.add(trainingDto);
        }
        return trainingDtos;
    }

    @Override
    public TrainingDto updateTraining(UUID id, TrainingUpdateDto trainingUpdateDto, MultipartFile image) {
        Optional<Training> existTraining = repository.findById(id);

        if(existTraining.isEmpty()) {
            throw FittrackException.withStatusAndMessage(HttpStatus.NOT_FOUND, ErrorMessages.TRAINING_NOT_FOUND);
        }

        Training training = existTraining.get();

        if(!authService.verifyUserIdMatchesAuthenticatedUser(training.getUserId())) {
            throw FittrackException.withStatusAndMessage(HttpStatus.FORBIDDEN, ErrorMessages.FORBIDDEN);
        }

        BeanUtils.copyProperties(trainingUpdateDto, training);

        String imageName = assetService.uploadImage(image);
        if(training.getImageName() != null && !training.getImageName().startsWith("https://")) {
            assetService.deleteImage(training.getImageName());
        }
        training.setImageName(imageName);

        Training responseTraining = repository.save(training);

        return convertTrainingData(responseTraining);
    }

    @Override
    public Boolean deleteTraining(UUID id) {
        Optional<Training> existTraining = repository.findById(id);

        if(existTraining.isEmpty()) {
            throw FittrackException.withStatusAndMessage(HttpStatus.NOT_FOUND, ErrorMessages.TRAINING_NOT_FOUND);
        }

        Training training = existTraining.get();

        if(!authService.verifyUserIdMatchesAuthenticatedUser(training.getUserId())) {
            throw FittrackException.withStatusAndMessage(HttpStatus.FORBIDDEN, ErrorMessages.FORBIDDEN);
        }

        if(training.getImageName() != null && !training.getImageName().startsWith("https://")) {
            assetService.deleteImage(training.getImageName());
        }
        repository.delete(training);

        return true;
    }
}
