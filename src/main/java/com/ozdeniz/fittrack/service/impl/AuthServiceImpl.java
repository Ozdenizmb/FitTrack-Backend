package com.ozdeniz.fittrack.service.impl;

import com.ozdeniz.fittrack.exception.ErrorMessages;
import com.ozdeniz.fittrack.exception.FittrackException;
import com.ozdeniz.fittrack.model.User;
import com.ozdeniz.fittrack.repository.UserRepository;
import com.ozdeniz.fittrack.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> existUser = repository.findByEmail(email);

        if (existUser.isPresent()) {
            User user = existUser.get();
            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getEmail())
                    .password(user.getPassword())
                    .roles("USER")
                    .build();
        }
        else {
            throw FittrackException.withStatusAndMessage(HttpStatus.BAD_REQUEST, ErrorMessages.EMAIL_NOT_FOUND);
        }
    }

    @Override
    public Boolean verifyUserIdMatchesAuthenticatedUser(UUID id) {
        String authenticatedUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> existUser = repository.findByEmail(authenticatedUserEmail);

        if (existUser.isEmpty()) {
            return false;
        }

        User authenticatedUser = existUser.get();
        return authenticatedUser.getId().equals(id);
    }

}
