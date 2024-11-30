package com.sprintly.sprintly.repository;


import com.sprintly.sprintly.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    //    User findByUsername(String username);  // cannot use orElse in this.
    Optional<User> findByUsername(String username);  // with optional, we can use orElse.

}
