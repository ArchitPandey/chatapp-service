package com.app.chat.service;

import org.springframework.web.socket.WebSocketSession;

public interface SessionService {

    public WebSocketSession getSessionFor(String user);

    public void saveSessionFor(String user, WebSocketSession session);

    public void removeSessionFor(String user);
}
