package com.ozdeniz.fittrack.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

public record TrainingDto(
        UUID id,
        UUID userId,
        String title,
        String description,
        LocalTime duration,
        String difficulty,
        String category,
        byte[] image,
        LocalDateTime createdDate,
        LocalDateTime updatedDate
) {
}
