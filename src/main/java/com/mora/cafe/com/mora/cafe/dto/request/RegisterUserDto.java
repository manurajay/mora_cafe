package com.mora.cafe.com.mora.cafe.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class RegisterUserDto {

    @NotBlank
    @Email
    @Size(max = 50)
    private String email;

    @NotBlank
    @Size(min = 4)
    private String username;

    private Set<String> role;

    @NotBlank
    @Size(min = 8, max = 40)
    private String password;

    @NotBlank
    private String fullName;

    @NotBlank
    @Size(min = 10, max = 10)
    private String contactNumber;




}
