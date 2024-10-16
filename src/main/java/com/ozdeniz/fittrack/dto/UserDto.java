package com.ozdeniz.fittrack.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserDto(
        UUID id,
        String firstName,
        String lastName,
        String email,
        String phone,
        String country,
        String city,
        LocalDateTime createdDate,
        LocalDateTime updatedDate
) {
}
