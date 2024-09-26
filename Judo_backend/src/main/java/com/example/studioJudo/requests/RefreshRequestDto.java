package com.example.studioJudo.requests;

import jakarta.validation.constraints.NotEmpty;

public record RefreshRequestDto(

        @NotEmpty
        String refreshToken
) {
}
