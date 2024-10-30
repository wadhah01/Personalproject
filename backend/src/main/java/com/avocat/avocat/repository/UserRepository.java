package com.avocat.avocat.repository;

import com.avocat.avocat.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username); // Custom query method
}
