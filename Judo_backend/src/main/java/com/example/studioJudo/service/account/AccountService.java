package com.example.studioJudo.service.account;

import com.example.studioJudo.dto.UserDto;
import com.example.studioJudo.models.User;
import com.example.studioJudo.requests.AuthenticationRequestDto;
import com.example.studioJudo.requests.CreateUserRequest;
import com.example.studioJudo.requests.RefreshRequestDto;
import com.example.studioJudo.responses.AuthenticationResponseDto;

public interface AccountService {

    UserDto signup(CreateUserRequest registerRequest);

    AuthenticationResponseDto login(AuthenticationRequestDto authenticationRequest, String role, Integer id);

//    AuthenticationResponseDto refresh(RefreshRequestDto refreshRequest);
//
//    void changePassword(ChangePasswordRequestDto changePasswordRequest);

//    void logout();
}
