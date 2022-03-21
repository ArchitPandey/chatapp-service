package com.app.chat.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/api/v1/chat")
public interface ChatController {

    @GetMapping("/friends/{user}")
    public List<String> getFriendsFor(@PathVariable(name = "user") String user);
}
