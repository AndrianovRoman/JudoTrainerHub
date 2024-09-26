package com.example.studioJudo.service;

import com.example.studioJudo.dto.SessionDto;
import com.example.studioJudo.models.Session;

import java.util.List;
import java.util.Optional;

public interface SessionService {
    List<SessionDto> findAllSession();
    SessionDto findSessionById(Integer id);

    SessionDto saveSession(SessionDto sessionDto);
    SessionDto save(SessionDto sessionDto);

    SessionDto updateSession(Integer id, SessionDto sessionDto);
    void deleteSession(Integer id);
}
