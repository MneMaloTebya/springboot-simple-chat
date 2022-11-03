package com.github.mnemalotebya.spingbootsimplechat.model;

import com.github.mnemalotebya.spingbootsimplechat.model.entity.Message;
import com.github.mnemalotebya.spingbootsimplechat.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, User> {
}
