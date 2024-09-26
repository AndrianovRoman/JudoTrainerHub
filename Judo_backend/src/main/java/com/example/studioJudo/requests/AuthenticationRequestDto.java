package com.example.studioJudo.requests;

import lombok.Builder;

@Builder
public record AuthenticationRequestDto (

        String login,

        String password

) {
}
