package com.example.studioJudo.mapper;

public interface Mapper<DTO, ENTITY> {

    DTO toDto(ENTITY entity);

    ENTITY toEntity(DTO dto);

}
