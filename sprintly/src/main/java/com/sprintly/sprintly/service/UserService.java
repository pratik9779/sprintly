package com.sprintly.sprintly.service;

import com.sprintly.sprintly.entity.User;
import com.sprintly.sprintly.model.UserRequest;

import java.util.List;

public interface UserService {

    User createUser(UserRequest userRequest);

    List<String> getUserAccounts(String username);
}
