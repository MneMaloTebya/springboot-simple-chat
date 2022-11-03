package com.github.mnemalotebya.spingbootsimplechat.controller;

import com.github.mnemalotebya.spingbootsimplechat.dto.DtoMessage;
import com.github.mnemalotebya.spingbootsimplechat.dto.MessageMapper;
import com.github.mnemalotebya.spingbootsimplechat.model.MessageRepository;
import com.github.mnemalotebya.spingbootsimplechat.model.UserRepository;
import com.github.mnemalotebya.spingbootsimplechat.model.entity.Message;
import com.github.mnemalotebya.spingbootsimplechat.model.entity.User;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class ChatController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping("/init")
    public HashMap<String, Boolean> init() {
        HashMap<String, Boolean> response = new HashMap<>();
        Map<String, Boolean> result = new HashMap<>();
        String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
        Optional<User> userOpt = userRepository.findBySessionId(sessionId);
        response.put("result", userOpt.isPresent());
        return response;
    }

    @PostMapping("/auth")
    public HashMap<String, Boolean> auth(@RequestParam String name) {
        HashMap<String, Boolean> response = new HashMap<>();
        String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
        User user = new User();
        user.setName(name);
        user.setSessionId(sessionId);
        userRepository.save(user);
        response.put("result", true);
        return response;
    }

    @PostMapping("/message")
    public Map<String, Boolean> sendMessage(@RequestParam String message) {
        if (Strings.isEmpty(message)) {
            return Map.of("result", false);
        }
        String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
        User user = userRepository.findBySessionId(sessionId).get();
        Message newMessage = new Message();
        newMessage.setDateTime(LocalDateTime.now());
        newMessage.setMessage(message);
        newMessage.setUser(user);
        messageRepository.saveAndFlush(newMessage);
        return Map.of("result", true);
    }

    @GetMapping("/message")
    public List<DtoMessage> getMessagesList() {
        return messageRepository
                .findAll(Sort.by(Sort.Direction.ASC, "dateTime"))
                .stream()
                .map(message -> MessageMapper.map(message))
                .collect(Collectors.toList());
    }

    @GetMapping("/user")
    public HashMap<Integer, String> getUsersList() {
        return null;
    }
}
