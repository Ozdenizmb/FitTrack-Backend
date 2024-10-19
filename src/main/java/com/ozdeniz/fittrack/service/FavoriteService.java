package com.ozdeniz.fittrack.service;

import java.util.UUID;

public interface FavoriteService {

    UUID addFavorite(UUID userId, UUID trainingId);
    Boolean removeFavorite(UUID userId, UUID trainingId);
    Boolean wasIFavorites(UUID userId, UUID trainingId);
    int getFavoriteCountForTraining(UUID trainingId);
    int getFavoriteCountForUser(UUID userId);

}
