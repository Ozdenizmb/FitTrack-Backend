package com.ozdeniz.fittrack.service;

import com.ozdeniz.fittrack.dto.UserCreateDto;
import com.ozdeniz.fittrack.dto.UserDto;
import com.ozdeniz.fittrack.dto.UserUpdateDto;

import java.util.UUID;

public interface UserService {

    UUID signUp(UserCreateDto userCreateDto);
    UserDto login(String email, String password);
    UserDto getUserById(UUID id);
    UserDto getUserByEmail(String email);
    UserDto updateUser(UUID id, UserUpdateDto userUpdateDto);
    Boolean deleteUser(UUID id);

}
