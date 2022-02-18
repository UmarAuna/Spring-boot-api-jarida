package com.jarida.server.jaridaserver.spring_security_2.exception;

import com.jarida.server.jaridaserver.spring_security_2.payload.ApiResponseTwos;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestExceptionTwos extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private ApiResponseTwos apiResponse;

    public BadRequestExceptionTwos(ApiResponseTwos apiResponse) {
        super();
        this.apiResponse = apiResponse;
    }

    public BadRequestExceptionTwos(String message) {
        super(message);
    }

    public BadRequestExceptionTwos(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiResponseTwos getApiResponse() {
        return apiResponse;
    }
}
