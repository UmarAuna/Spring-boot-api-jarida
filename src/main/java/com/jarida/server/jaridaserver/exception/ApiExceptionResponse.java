package com.jarida.server.jaridaserver.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class ApiExceptionResponse {

    private  String message;
    private String localizedMessage;
    private Integer statusCode;
    private HttpStatus httpStatus;
    private ZonedDateTime timeStamp;

    public ApiExceptionResponse() {
    }

    public ApiExceptionResponse(String message, Integer statusCode ,String localizedMessage, HttpStatus httpStatus, ZonedDateTime timeStamp) {
        this.message = message;
        this.statusCode = statusCode;
        this.localizedMessage = localizedMessage;
        this.httpStatus = httpStatus;
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public String getLocalizedMessage() {
        return localizedMessage;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setLocalizedMessage(String localizedMessage) {
        this.localizedMessage = localizedMessage;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public void setTimeStamp(ZonedDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ZonedDateTime getTimeStamp() {
        return timeStamp;
    }
}
