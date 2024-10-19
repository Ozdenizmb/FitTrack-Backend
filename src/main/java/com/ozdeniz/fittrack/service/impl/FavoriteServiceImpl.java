package com.ozdeniz.fittrack.service.impl;

import com.ozdeniz.fittrack.exception.ErrorMessages;
import com.ozdeniz.fittrack.exception.FittrackException;
import com.ozdeniz.fittrack.model.Favorite;
import com.ozdeniz.fittrack.repository.FavoriteRepository;
import com.ozdeniz.fittrack.service.AuthService;
import com.ozdeniz.fittrack.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FavoriteServiceImpl implements FavoriteService {

    private final FavoriteRepository repository;
    private final AuthService authService;

    @Override
    public UUID addFavorite(UUID userId, UUID trainingId) {
        System.out.println("selam");
        if(!authService.verifyUserIdMatchesAuthenticatedUser(userId)) {
            throw FittrackException.withStatusAndMessage(HttpStatus.FORBIDDEN, ErrorMessages.FORBIDDEN);
        }
        System.out.println("selam");
        Favorite favorite = new Favorite();
        favorite.setUserId(userId);
        favorite.setTrainingId(trainingId);
        System.out.println("selam");
        return repository.save(favorite).getId();
    }

    @Override
    public Boolean removeFavorite(UUID userId, UUID trainingId) {
        if(!authService.verifyUserIdMatchesAuthenticatedUser(userId)) {
            throw FittrackException.withStatusAndMessage(HttpStatus.FORBIDDEN, ErrorMessages.FORBIDDEN);
        }

        List<Favorite> existFavoriteForTraining = repository.findAllByTrainingId(trainingId);

        Optional<Favorite> dataToBeDeleted = existFavoriteForTraining.stream()
                .filter(favorite -> favorite.getUserId().equals(userId))
                .findFirst();

        if (dataToBeDeleted.isPresent()) {
            repository.delete(dataToBeDeleted.get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean wasIFavorites(UUID userId, UUID trainingId) {
        List<Favorite> existFavoriteForTraining = repository.findAllByTrainingId(trainingId);

        Optional<Favorite> dataToBeDeleted = existFavoriteForTraining.stream()
                .filter(favorite -> favorite.getUserId().equals(userId))
                .findFirst();

        return dataToBeDeleted.isPresent();
    }

    @Override
    public int getFavoriteCountForTraining(UUID trainingId) {
        List<Favorite> existFavoriteForTraining = repository.findAllByTrainingId(trainingId);
        return existFavoriteForTraining.size();
    }

    @Override
    public int getFavoriteCountForUser(UUID userId) {
        List<Favorite> existFavoriteForUser = repository.findAllByUserId(userId);
        return existFavoriteForUser.size();
    }
}
