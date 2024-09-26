package com.example.studioJudo.service;

import com.example.studioJudo.dto.BookingDto;

import java.util.List;

public interface BookingService {

    List<BookingDto> findAllBooking();
    BookingDto findBookingById(Integer id);

    BookingDto saveBooking(BookingDto bookingDto);
    BookingDto save(BookingDto bookingDto);

    BookingDto updateBooking(Integer id, BookingDto bookingDto);
    void deleteBooking(Integer id);
}
