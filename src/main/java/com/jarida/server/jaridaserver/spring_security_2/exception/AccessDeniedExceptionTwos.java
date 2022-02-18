package com.jarida.server.jaridaserver.spring_security_2.exception;

import com.jarida.server.jaridaserver.spring_security_2.payload.ApiResponseTwos;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class AccessDeniedExceptionTwos extends RuntimeException{
    private static final long serialVersionUID = 1L;

    private ApiResponseTwos apiResponse;

    private String message;

    public AccessDeniedExceptionTwos(ApiResponseTwos apiResponse) {
        super();
        this.apiResponse = apiResponse;
    }

    public AccessDeniedExceptionTwos(String message) {
        super(message);
        this.message = message;
    }

    public AccessDeniedExceptionTwos(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiResponseTwos getApiResponse() {
        return apiResponse;
    }

    public void setApiResponse(ApiResponseTwos apiResponse) {
        this.apiResponse = apiResponse;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
