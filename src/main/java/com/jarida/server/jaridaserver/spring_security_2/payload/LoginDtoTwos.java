package com.jarida.server.jaridaserver.spring_security_2.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@JsonPropertyOrder({
        "usernameOrEMail",
        "password"
})
public class LoginDtoTwos {

    @JsonProperty("usernameOrEMail")
    @NotEmpty(message = "Username or Email should not be null or empty")
    @Size(min = 4, max = 40)
    private String usernameOrEMail;

    @JsonProperty("password")
    @NotEmpty(message = "Password should not be null or empty")
    @Size(min = 6, max = 20)
    private String password;
}
