package com.example.studioJudo.service;

import com.example.studioJudo.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    // User
    List<UserDto> findAllUser();
    UserDto findUserById(Integer id);

    UserDto saveUser(UserDto userDto);
    UserDto updateUser(Integer id, UserDto userDto);
    void deleteUser(Integer id);

    UserDto findUserByEmail(String email);

    // Client All
    List<UserDto> findAllClient();

}
