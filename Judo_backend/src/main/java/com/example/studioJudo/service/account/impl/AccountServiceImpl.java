package com.example.studioJudo.service.account.impl;

import com.example.studioJudo.dto.UserDto;
import com.example.studioJudo.models.Role;
import com.example.studioJudo.models.User;
import com.example.studioJudo.repositories.RoleRepository;
import com.example.studioJudo.repositories.UserRepository;
import com.example.studioJudo.requests.AuthenticationRequestDto;
import com.example.studioJudo.requests.CreateUserRequest;
import com.example.studioJudo.responses.AuthenticationResponseDto;
import com.example.studioJudo.service.UserService;
import com.example.studioJudo.service.account.AccountService;
import com.example.studioJudo.service.account.AuthorityService;
import com.example.studioJudo.service.account.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    final PasswordEncoder passwordEncoder;
    final UserService userService;
    final RoleRepository roleRepository;
    final UserRepository userRepository;
    final AuthenticationManager authenticationManager;
    final JwtService jwtService;
    final AuthorityService authorityService;
//    final RefreshTokenRepository refreshTokenRepository;

    @Override
    @Transactional
    public UserDto signup(CreateUserRequest request) {
        Role role = roleRepository.findById(1)
                .orElseThrow(() -> new RuntimeException("Default role not found"));
        return userService.saveUser(UserDto.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phoneNumber(request.getPhoneNumber())
                .roleId(1)
                .isTrainer(false)
                .build());
    }

    @Override
    @Transactional
    public AuthenticationResponseDto login(AuthenticationRequestDto authenticationRequest, String role, Integer id) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.login(), authenticationRequest.password())
        );
        var user = userRepository.findUserByEmail(authenticationRequest.login())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        var roleName = user.getRole().getName();
        var userId = user.getId();
        return login(user, roleName, userId);
    }

//    @Override
//    @Transactional
//    public AuthenticationResponseDto refresh(RefreshRequestDto refreshRequest) {
//        var userId = Integer.parseInt(jwtService.extractRefreshUserId(refreshRequest.refreshToken()));
//        if(!refreshTokenRepository.existsByTokenAndCustomerId(refreshRequest.refreshToken(), userId)) {
//            throw new UsernameNotFoundException("Refresh token not found");
//        }
//        var user = userRepository.findByIdWithRoles(userId)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//        return login(user);
//    }

//    @Override
//    @Transactional
//    public void logout() {
//        Optional.ofNullable(authorityService.getCurrentUser())
//                .ifPresent(user -> refreshTokenRepository.deleteByUserId(user.getId()));
//    }

    private AuthenticationResponseDto login(User user, String role, Integer id) {
//        refreshTokenRepository.deleteByUserId(user.getId());
        return AuthenticationResponseDto.builder()
                .token(jwtService.generateToken(user))
                .role(role)
                .id(id)
//                .refreshToken(refreshTokenRepository.save(RefreshToken.builder()
//                        .user(user)
//                        .token(jwtService.generateRefreshToken(String.valueOf(user.getId())))
//                        .build()).getToken())
                .build();
    }
}
