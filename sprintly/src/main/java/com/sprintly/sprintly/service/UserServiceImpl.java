package com.sprintly.sprintly.service;

import com.sprintly.sprintly.config.WebSecurityConfig;
import com.sprintly.sprintly.entity.User;
import com.sprintly.sprintly.exception.custom.UserAlreadyPresent;
import com.sprintly.sprintly.model.UserRequest;
import com.sprintly.sprintly.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Inject PasswordEncoder directly.

    public User createUser(UserRequest userRequest) {
        log.info("Attempting to create user with username: {}", userRequest.getUsername());

        if (userRepository.findByUsername(userRequest.getUsername()).isPresent()) {
            log.warn("User with username {} already exists", userRequest.getUsername());
            throw new UserAlreadyPresent("User Already present");
        }

        User user = User
                .builder()
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .username(userRequest.getUsername())
                .emailID(userRequest.getEmailID())
                .build();

        userRepository.save(user);
        log.info("User created successfully with username: {}", user.getUsername());
        return user;
    }

    @Override
    public List<String> getUserAccounts(String username) {
        return null;
    }
}


