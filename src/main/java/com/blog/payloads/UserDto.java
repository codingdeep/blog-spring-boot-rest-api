package com.blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private Long id;
    @NotEmpty
    @Size(min = 4, message = "Please enter a name with at least 4 characters")
    private String name;
    @Email(message = "Email is not valid!")
    private String email;
    @NotEmpty
    @Size(min = 3, max = 10, message = "Please enter a password with min 3 or max 10 length")
    private String password;
    @NotEmpty
    private String about;


}
