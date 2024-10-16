package com.ozdeniz.fittrack.service.impl;

import com.ozdeniz.fittrack.dto.UserCreateDto;
import com.ozdeniz.fittrack.dto.UserDto;
import com.ozdeniz.fittrack.dto.UserUpdateDto;
import com.ozdeniz.fittrack.exception.ErrorMessages;
import com.ozdeniz.fittrack.exception.FittrackException;
import com.ozdeniz.fittrack.mapper.UserMapper;
import com.ozdeniz.fittrack.model.User;
import com.ozdeniz.fittrack.repository.UserRepository;
import com.ozdeniz.fittrack.service.AuthService;
import com.ozdeniz.fittrack.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthService authService;

    @Override
    public UUID signUp(UserCreateDto userCreateDto) {
        User user = new User();

        BeanUtils.copyProperties(userCreateDto, user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return repository.save(user).getId();
    }

    @Override
    public UserDto login(String email, String password) {
        Optional<User> existUser = repository.findByEmail(email);

        if (existUser.isEmpty()) {
            throw FittrackException.withStatusAndMessage(HttpStatus.NOT_FOUND, ErrorMessages.USER_NOT_FOUND);
        }

        User user = existUser.get();

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw FittrackException.withStatusAndMessage(HttpStatus.UNAUTHORIZED, ErrorMessages.INCORRECT_LOGIN);
        }

        return mapper.toDto(user);
    }

    @Override
    public UserDto getUserById(UUID id) {
        Optional<User> existUser = repository.findById(id);

        if (existUser.isEmpty()) {
            throw FittrackException.withStatusAndMessage(HttpStatus.NOT_FOUND, ErrorMessages.USER_NOT_FOUND);
        }

        return mapper.toDto(existUser.get());
    }

    @Override
    public UserDto getUserByEmail(String email) {
        Optional<User> existUser = repository.findByEmail(email);

        if (existUser.isEmpty()) {
            throw FittrackException.withStatusAndMessage(HttpStatus.NOT_FOUND, ErrorMessages.USER_NOT_FOUND);
        }

        return mapper.toDto(existUser.get());
    }

    @Override
    public UserDto updateUser(UUID id, UserUpdateDto userUpdateDto) {
        if(!authService.verifyUserIdMatchesAuthenticatedUser(id)) {
            throw FittrackException.withStatusAndMessage(HttpStatus.FORBIDDEN, ErrorMessages.FORBIDDEN);
        }

        Optional<User> existUser = repository.findById(id);

        if (existUser.isEmpty()) {
            throw FittrackException.withStatusAndMessage(HttpStatus.NOT_FOUND, ErrorMessages.USER_NOT_FOUND);
        }

        User user = existUser.get();
        BeanUtils.copyProperties(userUpdateDto, user);

        return mapper.toDto(repository.save(user));
    }

    @Override
    public Boolean deleteUser(UUID id) {
        if(!authService.verifyUserIdMatchesAuthenticatedUser(id)) {
            throw FittrackException.withStatusAndMessage(HttpStatus.FORBIDDEN, ErrorMessages.FORBIDDEN);
        }

        Optional<User> existUser = repository.findById(id);

        if (existUser.isEmpty()) {
            throw FittrackException.withStatusAndMessage(HttpStatus.NOT_FOUND, ErrorMessages.USER_NOT_FOUND);
        }

        repository.deleteById(id);

        return true;
    }
}
