package com.app.chat.service.impl;

import com.app.chat.service.SessionService;
import org.springframework.web.socket.WebSocketSession;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class SessionServiceImpl implements SessionService {

    private ConcurrentMap<String, WebSocketSession> sessions;

    public SessionServiceImpl() {
        sessions = new ConcurrentHashMap<>();
    }

    @Override
    public WebSocketSession getSessionFor(String user) {
        return sessions.get(user);
    }

    @Override
    public void saveSessionFor(String user, WebSocketSession session) {
        sessions.put(user, session);
    }

    @Override
    public void removeSessionFor(String user) {
        sessions.remove(user);
    }
}
