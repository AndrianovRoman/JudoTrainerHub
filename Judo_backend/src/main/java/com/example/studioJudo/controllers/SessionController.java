package com.example.studioJudo.controllers;

import com.example.studioJudo.dto.SessionDto;
import com.example.studioJudo.models.Session;
import com.example.studioJudo.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/session")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @GetMapping
    public List<SessionDto> findAllSession() {
        return sessionService.findAllSession();
    }

    @PostMapping
    public SessionDto saveSession(@RequestBody SessionDto sessionDto) {
        return sessionService.saveSession(sessionDto);
    }

    @GetMapping("/{id}")
    public SessionDto findSessionById(@PathVariable("id") Integer id) {
        return sessionService.findSessionById(id);
    }

    @PutMapping("/{id}")
    public SessionDto updateSession(@PathVariable("id") Integer id , @RequestBody SessionDto sessionDto) {
        return sessionService.updateSession(id, sessionDto);
    }

    @DeleteMapping("/{id}")
    public void deleteSession(@PathVariable("id") Integer id) {
        sessionService.deleteSession(id);
    }

}
