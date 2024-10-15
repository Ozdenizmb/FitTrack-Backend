package com.ozdeniz.fittrack.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserCreateDto(
        @NotBlank
        @NotNull
        String firstName,
        @NotBlank
        @NotNull
        String lastName,
        @Email
        @NotBlank
        @NotNull
        String email,
        @NotBlank
        @NotNull
        String password,
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
