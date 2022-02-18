package com.jarida.server.jaridaserver.spring_security_2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BlogAPIExceptionTwos extends RuntimeException {
    private HttpStatus status;
    private String message;

    public BlogAPIExceptionTwos(HttpStatus status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }

    public BlogAPIExceptionTwos(String message ,HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message;
        this.message = message1;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
