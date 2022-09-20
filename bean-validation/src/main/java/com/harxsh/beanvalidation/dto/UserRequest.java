package com.harxsh.beanvalidation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
public class UserRequest {

    private Long id;
    @NotNull(message = "name shouldn't be null")
    private String name;
    @Email(message = "please provide valid email address")
    private String email;
    @Pattern(regexp = "^\\d{10}$", message = "invalid mobile number")
    private String mobile;
    private String gender;
    @Min(18)
    @Max(60)
    private int age;
    @NotBlank
    private String country;
}
