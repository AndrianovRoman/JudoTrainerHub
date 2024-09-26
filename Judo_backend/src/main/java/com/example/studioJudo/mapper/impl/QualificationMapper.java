package com.example.studioJudo.mapper.impl;

import com.example.studioJudo.dto.QualificationDto;
import com.example.studioJudo.models.Qualification;
import com.example.studioJudo.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class QualificationMapper implements Mapper<QualificationDto, Qualification> {

    @Override
    public QualificationDto toDto(Qualification qualification) {
        return QualificationDto.builder()
                .id(qualification.getId())
                .name(qualification.getName())
                .build();
    }

    @Override
    public Qualification toEntity(QualificationDto qualificationDto) {
        return Qualification.builder()
                .id(qualificationDto.getId())
                .name(qualificationDto.getName())
                .build();
    }
}
