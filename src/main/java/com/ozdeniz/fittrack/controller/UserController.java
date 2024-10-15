package com.ozdeniz.fittrack.controller;

import com.ozdeniz.fittrack.api.UserApi;
import com.ozdeniz.fittrack.dto.UserCreateDto;
import com.ozdeniz.fittrack.dto.UserDto;
import com.ozdeniz.fittrack.dto.UserUpdateDto;
import com.ozdeniz.fittrack.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class UserController implements UserApi {

    private final UserService service;

    @Override
    public ResponseEntity<UUID> signUp(UserCreateDto userCreateDto) {
        return ResponseEntity.ok(service.signUp(userCreateDto));
    }

    @Override
    public ResponseEntity<UserDto> login(String email, String password) {
        return ResponseEntity.ok(service.login(email, password));
    }

    @Override
    public ResponseEntity<UserDto> getUserById(UUID id) {
        return ResponseEntity.ok(service.getUserById(id));
    }

    @Override
    public ResponseEntity<UserDto> getUserByEmail(String email) {
        return ResponseEntity.ok(service.getUserByEmail(email));
    }

    @Override
    public ResponseEntity<UserDto> updateUser(UUID id, UserUpdateDto userUpdateDto) {
        return ResponseEntity.ok(service.updateUser(id, userUpdateDto));
    }

    @Override
    public ResponseEntity<Boolean> deleteUser(UUID id) {
        return ResponseEntity.ok(service.deleteUser(id));
    }
}
