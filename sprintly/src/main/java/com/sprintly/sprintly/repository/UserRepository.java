package com.sprintly.sprintly.repository;


import com.sprintly.sprintly.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);  // with optional, we can use orElse.
    Optional<User> findByEmailID(String email);

}
