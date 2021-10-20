package com.jarida.server.jaridaserver.exception;

public class ResourceBadRequestException extends RuntimeException{

    public ResourceBadRequestException(String message) {
        super(message);
    }

    public ResourceBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
