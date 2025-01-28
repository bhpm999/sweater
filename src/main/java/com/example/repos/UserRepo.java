package com.example.repos;

import com.example.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.nio.file.LinkOption;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
