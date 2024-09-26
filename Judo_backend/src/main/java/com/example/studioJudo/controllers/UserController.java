package com.example.studioJudo.controllers;

import com.example.studioJudo.dto.UserDto;
import com.example.studioJudo.models.User;
import com.example.studioJudo.requests.AuthenticationRequestDto;
import com.example.studioJudo.requests.CreateUserRequest;
import com.example.studioJudo.requests.RefreshRequestDto;
import com.example.studioJudo.responses.AuthenticationResponseDto;
import com.example.studioJudo.service.TrainerService;
import com.example.studioJudo.service.UserService;
import com.example.studioJudo.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TrainerService trainerService;

    @Autowired
    AccountService accountService;

    @GetMapping
    public List<UserDto> findAllUser() {
        return userService.findAllUser();
    }

    @GetMapping("/{id}")
    public UserDto findUserById(@PathVariable("id") Integer id) {
        return userService.findUserById(id);
    }

    @PostMapping
    public UserDto saveUser(@RequestBody UserDto userDto) {
        return userService.saveUser(userDto);
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable("id") Integer id, @RequestBody UserDto userDto){
        return userService.updateUser(id, userDto);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Integer id){
        userService.deleteUser(id);
    }

    // Нахождение всех клиентов

    @GetMapping("/client")
    public List<UserDto> findAllClient() {
        return userService.findAllClient();
    }

    // создание, редактирование и удаление тренера!

    @GetMapping("/trainer")
    public List<UserDto> findAllTrainer() {
        return trainerService.findAllTrainer();
    }

//    @GetMapping("/trainer/{id}")
//    public UserDto findTrainerById(@PathVariable("id") Integer id) {
//        return userService.findTrainerById(id);
//    }

    @PostMapping("/trainer")
    public UserDto saveTrainer(@RequestBody UserDto userDto) {
        return trainerService.saveTrainer(userDto);
    }

    @PutMapping("/trainer/{id}")
    public UserDto updateTrainer(@PathVariable("id") Integer id, @RequestBody UserDto userDto){
        return trainerService.updateTrainer(id, userDto);
    }

    @DeleteMapping("/trainer/{id}")
    public void deleteTrainer(@PathVariable("id") Integer id){
        trainerService.deleteTrainer(id);
    }

    // реистрация и вход

    @PostMapping("/login")
    public AuthenticationResponseDto login(@RequestBody AuthenticationRequestDto request, String role, Integer id){
        return accountService.login(request, role, id);
    }

    @PostMapping("/signup")
    public UserDto signup(@RequestBody CreateUserRequest request){
        return accountService.signup(request);
    }

//    @PostMapping("/refresh")
//    public AuthenticationResponseDto refresh(@RequestBody RefreshRequestDto request) {
//        return accountService.refresh(request);
//    }

//    @PostMapping("/logout")
//    public ResponseEntity<Void> logout() {
//        accountService.logout();
//        return ResponseEntity.noContent().build();
//    }

}