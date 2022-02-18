package com.jarida.server.jaridaserver.spring_security_2.payload;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Api(value = "Comment model information")
@Data
public class CommentDtoTwos {
    // @ApiModelProperty(hidden = true)
    @ApiModelProperty(value = "Comment id")
    private Long id;

    //name should not be null or empty
    @ApiModelProperty(value = "Comment name")
    @NotEmpty(message = "Name should not be null or empty")
    private String name;

    //email should not be null or empty
    //email validation field
    @ApiModelProperty(value = "Comment email")
    @NotEmpty(message = "Email should not be null or empty")
    @Email
    private String email;

    //comment should not be null or empty
    //Comment body must be minimum 10 characters
    @ApiModelProperty(value = "Comment body")
    @NotEmpty(message = "Comment should not be null or empty")
    @Size(min = 10, message = "Comment body must be minimum 10 characters")
    private String body;
}
