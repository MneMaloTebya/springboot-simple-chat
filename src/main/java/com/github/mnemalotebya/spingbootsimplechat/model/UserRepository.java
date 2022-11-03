package com.github.mnemalotebya.spingbootsimplechat.model;

import com.github.mnemalotebya.spingbootsimplechat.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findBySessionId(String sessionId);
}
