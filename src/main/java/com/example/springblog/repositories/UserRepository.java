package com.example.springblog.repositories;

import com.example.springblog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // JPA will configure the query.
    User findByUsername(String username);

}