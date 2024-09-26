package com.example.studioJudo.service.impl;

import com.example.studioJudo.dto.QualificationDto;
import com.example.studioJudo.mapper.impl.QualificationMapper;
import com.example.studioJudo.repositories.QualificationRepository;
import com.example.studioJudo.service.QualificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QualificationServiceImpl implements QualificationService {

    @Autowired
    private QualificationRepository qualificationRepository;

    @Autowired
    private QualificationMapper qualificationMapper;

    @Override
    public List<QualificationDto> findAllQualification() {
        return qualificationRepository.findAll().stream()
                .map(qualificationMapper::toDto)
                .toList();
    }

    @Override
    public QualificationDto findQualificationById(Integer id) {
        return qualificationRepository.findById(id).stream()
                .map(qualificationMapper::toDto)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Qualification not found"));
    }
}
