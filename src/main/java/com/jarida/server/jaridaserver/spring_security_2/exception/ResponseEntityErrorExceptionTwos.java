package com.jarida.server.jaridaserver.spring_security_2.exception;

import com.jarida.server.jaridaserver.spring_security_2.payload.ApiResponseTwos;
import org.springframework.http.ResponseEntity;

public class ResponseEntityErrorExceptionTwos extends RuntimeException {
    private static final long serialVersionUID = -3156815846745801694L;

    private transient ResponseEntity<ApiResponseTwos> apiResponse;

    public ResponseEntityErrorExceptionTwos(ResponseEntity<ApiResponseTwos> apiResponse) {
        this.apiResponse = apiResponse;
    }

    public ResponseEntity<ApiResponseTwos> getApiResponse() {
        return apiResponse;
    }
}
