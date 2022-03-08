package com.app.chat.handler;

import com.app.chat.entity.ChatMessage;
import com.app.chat.service.SessionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Objects;

import static com.app.chat.constants.AppConstants.SYSTEM;
import static com.app.chat.constants.AppConstants.USER;

@Slf4j
public class ChatMessageHandler extends TextWebSocketHandler {

    private ObjectMapper objectMapper;
    private SessionService sessionService;

    public ChatMessageHandler(ObjectMapper objectMapper, SessionService sessionService) {
        this.objectMapper = objectMapper;
        this.sessionService = sessionService;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        String user = (String) session.getAttributes().get(USER);
        sessionService.saveSessionFor(user, session);
    }


    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws IOException {
        ChatMessage chatMessage = objectMapper.readValue(textMessage.getPayload(), ChatMessage.class);
        WebSocketSession receiver = this.sessionService.getSessionFor(chatMessage.getTo());

        if(Objects.isNull(receiver)) {
            ChatMessage offline = ChatMessage
                    .builder()
                    .from(SYSTEM)
                    .to(chatMessage.getFrom())
                    .message(chatMessage.getTo()+" is offline!")
                    .build();

            String reply = objectMapper.writeValueAsString(offline);
            session.sendMessage( new TextMessage(reply));
        } else {
            String forwardMessage = objectMapper.writeValueAsString(chatMessage);
            receiver.sendMessage(new TextMessage(forwardMessage));
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        String user = (String) session.getAttributes().get(USER);
        sessionService.removeSessionFor(user);
    }
}
