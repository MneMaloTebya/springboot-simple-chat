package com.github.mnemalotebya.spingbootsimplechat.controller;

import com.github.mnemalotebya.spingbootsimplechat.model.MessageRepository;
import com.github.mnemalotebya.spingbootsimplechat.model.UserRepository;
import com.github.mnemalotebya.spingbootsimplechat.model.entity.Message;
import com.github.mnemalotebya.spingbootsimplechat.model.entity.User;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
public class ChatController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/init")
    public HashMap<String, Boolean> init() {
        HashMap<String, Boolean> response = new HashMap<>();
        String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
        Optional<User> optional = userRepository.findBySessionId(sessionId);
        response.put("result", optional.isPresent());
        return response;
    }

    @PostMapping("/auth")
    public HashMap<String, Boolean> auth(@RequestParam String name) {
        HashMap<String, Boolean> response = new HashMap<>();
        if (!Strings.isNotEmpty(name)) {
            response.put("result", false);
            return response;
        }
        String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
        User user = new User();
        user.setName(name);
        user.setSessionId(sessionId);
        userRepository.save(user);
        response.put("result", true);
        return response;
    }

    @PostMapping("/message")
    public HashMap<String, Boolean> sendMessage(@RequestParam String message) {
        HashMap<String, Boolean> response = new HashMap<>();
        if (Strings.isEmpty(message)){
            response.put("result", false);
            return response;
        }
        String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
        User user = userRepository.findBySessionId(sessionId).get();
        Message newMessage = new Message();
        newMessage.setDateTime(LocalDateTime.now());
        newMessage.setMessage(message);
        newMessage.setUser(user);
        messageRepository.save(newMessage);
        response.put("result", true);
        return response;
    }

    @GetMapping("/message")
    public List<String> getMessagesList() {
        return null;
    }

    @GetMapping("/user")
    public HashMap<Integer, String> getUsersList() {
        return null;
    }
}
