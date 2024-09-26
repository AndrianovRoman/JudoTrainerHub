package com.example.studioJudo.service.impl;

import com.example.studioJudo.dto.BookingDto;
import com.example.studioJudo.dto.SessionDto;
import com.example.studioJudo.mapper.impl.BookingMapper;
import com.example.studioJudo.mapper.impl.SessionMapper;
import com.example.studioJudo.models.Booking;
import com.example.studioJudo.models.Session;
import com.example.studioJudo.models.User;
import com.example.studioJudo.repositories.BookingRepository;
import com.example.studioJudo.repositories.SessionRepository;
import com.example.studioJudo.repositories.UserRepository;
import com.example.studioJudo.service.BookingService;
import com.example.studioJudo.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private BookingMapper bookingMapper;

    @Override
    public List<BookingDto> findAllBooking() {
        return bookingRepository.findAll().stream()
                .map(bookingMapper::toDto)
                .toList();
    }

    @Override
    public BookingDto findBookingById(Integer id) {
        return bookingRepository.findById(id).stream()
                .map(bookingMapper::toDto)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    @Override
    public BookingDto saveBooking(BookingDto bookingDto) {
        return this.save(BookingDto.builder()
                    .userId(nonNull(bookingDto.getUserId()) ? bookingDto.getUserId() : null)
                    .sessionId(nonNull(bookingDto.getSessionId()) ? bookingDto.getSessionId() : null)
                .build());
    }

    @Override
    public BookingDto save(BookingDto bookingDto) {
        Booking booking = bookingMapper.toEntity(bookingDto);
        return bookingMapper.toDto(bookingRepository.save(booking));
    }

    @Override
    public BookingDto updateBooking(Integer id, BookingDto bookingDto) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

//        booking.setUser(nonNull(bookingDto.getUserId()) ? bookingDto.getUserId()
//                : (nonNull(booking.getUser()) ? booking.getUser() : null);

        Optional<User> userOptional = userRepository.findById(bookingDto.getUserId());
        if (userOptional.isPresent()) {
            booking.setUser(userOptional.get());
        } else {
            booking.setUser(null);
        }

        Optional<Session> sessionOptional = sessionRepository.findById(bookingDto.getSessionId());
        if (sessionOptional.isPresent()) {
            booking.setSession(sessionOptional.get());
        } else {
            booking.setSession(null);
        }

        return bookingMapper.toDto(bookingRepository.save(booking));
    }

    @Override
    public void deleteBooking(Integer id) {
        bookingRepository.deleteById(id);
    }
}
