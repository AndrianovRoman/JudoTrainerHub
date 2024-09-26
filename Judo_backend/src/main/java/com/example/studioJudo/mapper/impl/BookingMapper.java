package com.example.studioJudo.mapper.impl;

import com.example.studioJudo.dto.BookingDto;
import com.example.studioJudo.dto.QualificationDto;
import com.example.studioJudo.mapper.Mapper;
import com.example.studioJudo.models.*;
import org.springframework.stereotype.Component;

import static java.util.Objects.nonNull;

@Component
public class BookingMapper implements Mapper<BookingDto, Booking> {

    @Override
    public BookingDto toDto(Booking booking) {
        return BookingDto.builder()
                .id(booking.getId())
                .userId(booking.getUser().getId())
                .sessionId(booking.getSession().getId())
                .build();
    }

    @Override
    public Booking toEntity(BookingDto bookingDto) {
        return Booking.builder()
                .id(bookingDto.getId())
                .user(nonNull(bookingDto.getUserId()) ?
                        User.builder().id(bookingDto.getUserId()).build() : null)
                .session(nonNull(bookingDto.getSessionId()) ?
                        Session.builder().id(bookingDto.getSessionId()).build() : null)
                .build();
    }
}
