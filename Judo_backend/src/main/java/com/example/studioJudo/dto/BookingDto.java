package com.example.studioJudo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class BookingDto {

    private Integer id;
    private Integer userId;
    private Integer sessionId;

}
