package com.app.chat.rest.impl;

import com.app.chat.rest.ChatController;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ChatControllerImpl implements ChatController {

    @Override
    public List<String> getFriendsFor(String user) {
        return Arrays.asList("John", "Paul", "Dave");
    }
}
