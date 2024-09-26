package com.example.studioJudo.mapper.impl;

import com.example.studioJudo.dto.SessionDto;
import com.example.studioJudo.mapper.Mapper;
import com.example.studioJudo.models.Booking;
import com.example.studioJudo.models.Qualification;
import com.example.studioJudo.models.Session;
import com.example.studioJudo.models.User;
import org.springframework.stereotype.Component;

import static java.util.Objects.nonNull;

@Component
public class SessionMapper implements Mapper<SessionDto, Session> {

    @Override
    public SessionDto toDto(Session session) {
        return SessionDto.builder()
                .id(session.getId())
                .sessionDate(session.getSessionDate())
                .startTime(session.getStartTime())
                .endTime(session.getEndTime())
                .description(session.getDescription())
//                .bookingId(session.getBooking().getId())
//                .userId(session.getUser().getId())
                .build();
    }

    @Override
    public Session toEntity(SessionDto sessionDto) {
        return Session.builder()
                .id(sessionDto.getId())
                .sessionDate(sessionDto.getSessionDate())
                .startTime(sessionDto.getStartTime())
                .endTime(sessionDto.getEndTime())
                .description(sessionDto.getDescription())
//                .booking(nonNull(sessionDto.getBookingId()) ?
//                        Booking.builder().id(sessionDto.getBookingId()).build() : null)
//                .user(nonNull(sessionDto.getUserId()) ?
//                        User.builder().id(sessionDto.getUserId()).build() : null)
                .build();
    }
}
