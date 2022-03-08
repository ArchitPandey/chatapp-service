package com.app.chat.config;

import com.app.chat.handler.ChatMessageHandler;
import com.app.chat.service.SessionService;
import com.app.chat.service.impl.SessionServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public SessionService sessionService() {
        return new SessionServiceImpl();
    }

    @Bean
    public ChatMessageHandler chatMessageHandler(@Qualifier(value = "objectMapper") ObjectMapper objectMapper, @Qualifier("sessionService") SessionService sessionService) {
        return new ChatMessageHandler(objectMapper, sessionService);
    }
}
