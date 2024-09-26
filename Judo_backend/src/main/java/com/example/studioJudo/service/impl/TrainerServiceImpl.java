package com.example.studioJudo.service.impl;

import com.example.studioJudo.dto.UserDto;
import com.example.studioJudo.mapper.impl.UserMapper;
import com.example.studioJudo.models.Qualification;
import com.example.studioJudo.models.Role;
import com.example.studioJudo.models.User;
import com.example.studioJudo.repositories.QualificationRepository;
import com.example.studioJudo.repositories.RoleRepository;
import com.example.studioJudo.repositories.UserRepository;
import com.example.studioJudo.service.TrainerService;
import com.example.studioJudo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;

@Service
public class TrainerServiceImpl implements TrainerService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private QualificationRepository qualificationRepository;

    @Override
    public List<UserDto> findAllTrainer() {
        Role role = roleRepository.findById(2).orElseThrow(() -> new RuntimeException("Role not found"));
        return userRepository.findByRole(role).stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Override
    public UserDto saveTrainer(UserDto userDto) {

        Optional<Qualification> qualificationOptional = qualificationRepository.findById(userDto.getQualificationId());
        Integer qualification = 1;
        if (qualificationOptional.isPresent()) {
            qualification = qualificationOptional.get().getId();
        }

        return userService.saveUser(UserDto.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .phoneNumber(userDto.getPhoneNumber())
                .roleId(2)
                .isTrainer(true)
                .qualificationId(qualification)
                .build());
    }

    @Override
    public UserDto updateTrainer(Integer id, UserDto userDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setFirstName(nonNull(userDto.getFirstName()) ? userDto.getFirstName() : user.getFirstName());
        user.setLastName(nonNull(userDto.getLastName()) ? userDto.getLastName() : user.getLastName());
        user.setEmail(nonNull(userDto.getEmail()) ? userDto.getEmail() : user.getEmail());
        user.setPassword(user.getPassword());
        user.setPhoneNumber(nonNull(userDto.getPhoneNumber()) ? userDto.getPhoneNumber() : user.getPhoneNumber());

        Optional<Qualification> qualificationOptional = qualificationRepository.findById(userDto.getQualificationId());
        if (qualificationOptional.isPresent()) {
            user.setQualification(qualificationOptional.get());
        }

        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public void deleteTrainer(Integer id) {
        userRepository.deleteById(id);
    }

}
