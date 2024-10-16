package com.ozdeniz.fittrack.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public record TrainingUpdateDto(
        @NotBlank
        @NotNull
        String title,
        @NotBlank
        @NotNull
        String description,
        @NotNull
        LocalTime duration,
        @NotNull
        int category
) {
}
