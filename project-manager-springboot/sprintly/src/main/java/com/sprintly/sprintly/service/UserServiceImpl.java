package com.sprintly.sprintly.service;

import com.sprintly.sprintly.entity.User;
import com.sprintly.sprintly.exception.custom.UserAlreadyPresent;
import com.sprintly.sprintly.model.UserRequest;
import com.sprintly.sprintly.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    // Add exception
    public User createUser(UserRequest userRequest) {

        if (userRepository.findByUsername(userRequest.getUsername()).isPresent()) {
            throw new UserAlreadyPresent("User Already present");
        }

        User user = User
                .builder()
                .password(userRequest.getPassword())
                .username(userRequest.getUsername())
                .emailID(userRequest.getEmailID())
                .build();

        userRepository.save(user);
        return user;
    }

    @Override
    public List<String> getUserAccounts(String username) {
        return null;
    }

}
