package com.example.studioJudo.service.impl;

import com.example.studioJudo.dto.SessionDto;
import com.example.studioJudo.mapper.impl.QualificationMapper;
import com.example.studioJudo.mapper.impl.SessionMapper;
import com.example.studioJudo.models.Booking;
import com.example.studioJudo.models.Session;
import com.example.studioJudo.models.User;
import com.example.studioJudo.repositories.SessionRepository;
import com.example.studioJudo.repositories.UserRepository;
import com.example.studioJudo.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;

@Service
public class SessionServiceImpl implements SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionMapper sessionMapper;

    @Override
    public List<SessionDto> findAllSession() {
        return sessionRepository.findAll().stream()
                .map(sessionMapper::toDto)
                .toList();
    }

    @Override
    public SessionDto findSessionById(Integer id) {
        return sessionRepository.findById(id).stream()
                .map(sessionMapper::toDto)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Session not found"));
    }

    @Override
    public SessionDto saveSession(SessionDto sessionDto) {
        return this.save(SessionDto.builder()
                .sessionDate(sessionDto.getSessionDate())
                .startTime(sessionDto.getStartTime())
                .endTime(sessionDto.getEndTime())
                .description(sessionDto.getDescription())
//                .bookingId(nonNull(sessionDto.getBookingId()) ? sessionDto.getBookingId() : null)
//                .userId(nonNull(sessionDto.getUserId()) ? sessionDto.getUserId() : null)
                .build());
    }

    @Override
    public SessionDto save(SessionDto sessionDto) {
        Session session = sessionMapper.toEntity(sessionDto);
        return sessionMapper.toDto(sessionRepository.save(session));
    }

    @Override
    public SessionDto updateSession(Integer id, SessionDto sessionDto) {
        Session session = sessionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Session not found"));

        session.setSessionDate(nonNull(sessionDto.getSessionDate()) ? sessionDto.getSessionDate() : session.getSessionDate());
        session.setStartTime(nonNull(sessionDto.getStartTime()) ? sessionDto.getStartTime() : session.getStartTime());
        session.setEndTime(nonNull(sessionDto.getEndTime()) ? sessionDto.getEndTime() : session.getEndTime());
        session.setDescription(nonNull(sessionDto.getDescription()) ? sessionDto.getDescription() : session.getDescription());

//        Optional<Booking> bookingOptional = bookingRepository.findById(sessionDto.getBookingId());
//        if (bookingOptional.isPresent()) {
//            session.setBooking(bookingOptional.get());
//        } else {
//            session.setBooking(null);
//        }

//        session.setBooking(nonNull(sessionDto.getBookingId()) ? sessionDto.getBookingId()
//                : (nonNull(session.getBooking()) ? session.getBooking() : null));

//        Optional<User> userOptional = userRepository.findById(sessionDto.getUserId());
//        if (userOptional.isPresent()) {
//            session.setUser(userOptional.get());
//        } else {
//            session.setUser(null);
//        }

//        session.setUser(nonNull(sessionDto.getUserId()) ? sessionDto.getUserId()
//                : (nonNull(session.getUser()) ? session.getUser() : null));

        return sessionMapper.toDto(sessionRepository.save(session));
    }

    @Override
    public void deleteSession(Integer id) {
        sessionRepository.deleteById(id);
    }
}
