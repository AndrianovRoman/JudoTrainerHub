package com.example.studioJudo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Setter
@Getter
@Builder
public class SessionDto {

    private Integer id;
    private LocalDate sessionDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private String description;

//    @JsonIgnore
//    private Integer bookingId;

//    private Integer userId;

}
