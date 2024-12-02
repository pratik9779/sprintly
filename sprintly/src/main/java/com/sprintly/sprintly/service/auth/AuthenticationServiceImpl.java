package com.sprintly.sprintly.service.auth;

import com.sprintly.sprintly.entity.User;
import com.sprintly.sprintly.exception.custom.UserAlreadyPresent;
import com.sprintly.sprintly.model.auth.LoginUserDto;
import com.sprintly.sprintly.model.auth.RegisterUserDto;
import com.sprintly.sprintly.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Inject PasswordEncoder directly.

    @Autowired
    private AuthenticationManager authenticationManager;

    public User registerUser(RegisterUserDto registerUserDto) {
        log.info("Attempting to create user with emailID: {}", registerUserDto.getEmailID());

        if (userRepository.findByEmailID(registerUserDto.getEmailID()).isPresent()) {
            log.warn("User with email {} already exists", registerUserDto.getEmailID());
            throw new UserAlreadyPresent("User Already present");
        }

        User user = User
                .builder()
                .firstName(registerUserDto.getFirstName())
                .lastName(registerUserDto.getLastName())
                .password(passwordEncoder.encode(registerUserDto.getPassword()))
                .emailID(registerUserDto.getEmailID())
                .enabled(true)
                .build();

        userRepository.save(user);
        log.info("User created successfully with username: {}", user.getUsername());
        return user;
    }

    // DaoAuthenticationProvider is responsible for feting user fron UserDetailsSrvice
    public User authenticate(LoginUserDto loginUserDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUserDto.getEmailID(),
                        loginUserDto.getPassword()
                )
        );

        return userRepository.findByEmailID(loginUserDto.getEmailID())
                .orElseThrow();
    }

    @Override
    public List<String> getUserAccounts(String username) {
        return null;
    }
}


