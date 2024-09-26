package com.example.studioJudo.controllers;

import com.example.studioJudo.dto.QualificationDto;
import com.example.studioJudo.service.QualificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/qualification")
public class QualificationController {

    @Autowired
    private QualificationService qualificationService;

    @GetMapping
    public List<QualificationDto> findAllQualification() {
        return qualificationService.findAllQualification();
    }

    @GetMapping("/{id}")
    public QualificationDto findQualificationById(@PathVariable("id") Integer id) {
        return qualificationService.findQualificationById(id);
    }

}
