package com.example.studioJudo.service;

import com.example.studioJudo.dto.UserDto;
import com.example.studioJudo.models.User;

import java.util.List;

public interface TrainerService {

    List<UserDto> findAllTrainer();
    UserDto saveTrainer(UserDto userDto);
    UserDto updateTrainer(Integer id, UserDto userDto);
    void deleteTrainer(Integer id);

}
