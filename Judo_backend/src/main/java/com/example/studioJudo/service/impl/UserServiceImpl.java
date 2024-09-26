package com.example.studioJudo.service.impl;

import com.example.studioJudo.dto.UserDto;
import com.example.studioJudo.mapper.impl.UserMapper;
import com.example.studioJudo.models.Qualification;
import com.example.studioJudo.models.Role;
import com.example.studioJudo.models.User;
import com.example.studioJudo.repositories.QualificationRepository;
import com.example.studioJudo.repositories.RoleRepository;
import com.example.studioJudo.repositories.UserRepository;
import com.example.studioJudo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Objects.nonNull;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private QualificationRepository qualificationRepository;

    //User
    @Override
    public List<UserDto> findAllUser() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Override // новое
    public UserDto findUserById(Integer id) {

        return userRepository.findById(id).stream()
                .map(userMapper::toDto)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Override
    public UserDto findUserByEmail(String email) {
        return userRepository.findUserByEmail(email).stream()
                .map(userMapper::toDto)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User by email not found"));
    }

    @Override
    public UserDto saveUser(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public UserDto updateUser(Integer id, UserDto userDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setFirstName(nonNull(userDto.getFirstName()) ? userDto.getFirstName() : user.getFirstName());
        user.setLastName(nonNull(userDto.getLastName()) ? userDto.getLastName() : user.getLastName());
        user.setEmail(nonNull(userDto.getEmail()) ? userDto.getEmail() : user.getEmail());
        user.setPassword(user.getPassword());
        user.setPhoneNumber(nonNull(userDto.getPhoneNumber()) ? userDto.getPhoneNumber() : user.getPhoneNumber());

        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    //Client

    @Override
    public List<UserDto> findAllClient() {
        Role role = roleRepository.findById(1).orElseThrow(() -> new RuntimeException("Role not found"));
        return userRepository.findByRole(role).stream()
                .map(userMapper::toDto)
                .toList();
    }
}
