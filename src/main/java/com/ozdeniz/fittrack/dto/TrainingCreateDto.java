package com.ozdeniz.fittrack.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;
import java.util.UUID;

public record TrainingCreateDto(
        @NotNull
        UUID userId,
        @NotBlank
        @NotNull
        String title,
        @NotBlank
        @NotNull
        String description,
        @NotNull
        LocalTime duration,
        @NotNull
        int difficulty,
        @NotNull
        int category
) {
}
