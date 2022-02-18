package com.jarida.server.jaridaserver.spring_security_2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadCredentialsExceptionTwos extends RuntimeException {
    private HttpStatus status;
    private String message;

    public BadCredentialsExceptionTwos(HttpStatus status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }

    public BadCredentialsExceptionTwos(String message , HttpStatus status, String message1) {
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
