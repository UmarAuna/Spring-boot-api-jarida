package com.jarida.server.jaridaserver.spring_security_2.payload;

import lombok.Data;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class ErrorDetailsResponseTwo {
    private String error;
    private Integer status;
    private List<String> messages;
    private String timestamp;

    public ErrorDetailsResponseTwo(List<String> messages, String error, Integer status) {
        setMessages(messages);
        this.error = error;
        this.status = status;
        this.timestamp = Instant.now().toString();
    }

    public List<String> getMessages() {

        return messages == null ? null : new ArrayList<>(messages);
    }

    public final void setMessages(List<String> messages) {

        if (messages == null) {
            this.messages = null;
        } else {
            this.messages = Collections.unmodifiableList(messages);
        }
    }
}
