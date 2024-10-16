package com.ozdeniz.fittrack.dto;

import java.util.UUID;

public record TrainingDto(
        UUID id,
        UUID userId,
        String title,
        String description,
        String duration,
        String category,
        String imageName
) {
}
