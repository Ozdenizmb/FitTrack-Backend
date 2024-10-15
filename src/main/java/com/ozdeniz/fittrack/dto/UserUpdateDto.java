package com.ozdeniz.fittrack.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserUpdateDto(
        @NotBlank
        @NotNull
        String firstName,
        @NotBlank
        @NotNull
        String lastName,
        @NotBlank
        @NotNull
        String phone,
        @NotBlank
        @NotNull
        String country,
        @NotBlank
        @NotNull
        String city
) {
}
