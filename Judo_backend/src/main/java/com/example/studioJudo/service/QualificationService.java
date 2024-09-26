package com.example.studioJudo.service;

import com.example.studioJudo.dto.QualificationDto;
import com.example.studioJudo.models.Qualification;

import java.util.List;
import java.util.Optional;

public interface QualificationService {

    List<QualificationDto> findAllQualification();

    QualificationDto findQualificationById(Integer id);
}
