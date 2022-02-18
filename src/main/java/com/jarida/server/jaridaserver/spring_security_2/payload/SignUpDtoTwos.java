package com.jarida.server.jaridaserver.spring_security_2.payload;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class SignUpDtoTwos {

    @NotEmpty(message = "First name should not be null or empty")
    @Size(min = 4, max = 40)
    private String firstName;

    @NotEmpty(message = "Last name should not be null or empty")
    @Size(min = 4, max = 40)
    private String lastName;

    @NotEmpty(message = "Phone number should not be null or empty")
    @Size(min = 4, max = 40)
    private String phoneNumber;

    @NotEmpty(message = "Username should not be null or empty")
    @Size(min = 4, max = 40)
    private String userName;

    @Size(max = 40)
    @NotEmpty(message = "Email should not be null or empty")
    @Email(message = "Email not properly formatted")
    private String email;


    @NotEmpty(message = "Password should not be null or empty")
    @Size(min = 6, max = 20)
    private String password;
}
