package com.jarida.server.jaridaserver.exception;

public class ResourceUnsupportedMediaTypeException extends RuntimeException{

    public ResourceUnsupportedMediaTypeException(String message) {
        super(message);
    }

    public ResourceUnsupportedMediaTypeException(String message, Throwable cause) {
        super(message, cause);
    }
}
