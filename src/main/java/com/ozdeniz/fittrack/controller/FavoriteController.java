package com.ozdeniz.fittrack.controller;

import com.ozdeniz.fittrack.api.FavoriteApi;
import com.ozdeniz.fittrack.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class FavoriteController implements FavoriteApi {

    private final FavoriteService service;

    @Override
    public ResponseEntity<UUID> addFavorite(UUID userId, UUID trainingId) {
        return ResponseEntity.ok(service.addFavorite(userId, trainingId));
    }

    @Override
    public ResponseEntity<Boolean> removeFavorite(UUID userId, UUID trainingId) {
        return ResponseEntity.ok(service.removeFavorite(userId, trainingId));
    }

    @Override
    public ResponseEntity<Boolean> wasIFavorites(UUID userId, UUID trainingId) {
        return ResponseEntity.ok(service.wasIFavorites(userId, trainingId));
    }

    @Override
    public ResponseEntity<Integer> getFavoriteCountForTraining(UUID trainingId) {
        return ResponseEntity.ok(service.getFavoriteCountForTraining(trainingId));
    }

    @Override
    public ResponseEntity<Integer> getFavoriteCountForUser(UUID userId) {
        return ResponseEntity.ok(service.getFavoriteCountForUser(userId));
    }
}
