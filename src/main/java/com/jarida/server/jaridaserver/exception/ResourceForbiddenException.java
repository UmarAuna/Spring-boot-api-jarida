package com.jarida.server.jaridaserver.exception;

public class ResourceForbiddenException extends RuntimeException{

    public ResourceForbiddenException(String message) {
        super(message);
    }

    public ResourceForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }
}
