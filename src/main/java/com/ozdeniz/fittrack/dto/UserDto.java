package com.ozdeniz.fittrack.dto;

import java.util.UUID;

public record UserDto(
        UUID id,
        String firstName,
        String lastName,
        String email,
        String phone,
        String country,
        String city
) {
}
