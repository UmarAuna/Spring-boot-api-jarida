package com.jarida.server.jaridaserver.spring_security_2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class AppExceptionTwos extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public AppExceptionTwos(String message) {
        super(message);
    }

    public AppExceptionTwos(String message, Throwable cause) {
        super(message, cause);
    }
}
