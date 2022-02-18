package com.jarida.server.jaridaserver.spring_security_2.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class UpdatePhoneNumberRequestTwos {

    @NotEmpty(message = "Phone number should not be null or empty")
    @Size(min = 4, max = 40)
    private String phoneNumber;

}
