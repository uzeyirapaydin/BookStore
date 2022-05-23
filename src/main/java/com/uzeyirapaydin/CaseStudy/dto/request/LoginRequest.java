package com.uzeyirapaydin.CaseStudy.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {

    @NotBlank(message = "Username must be filled!")
    private String email;
    @NotBlank(message = "Password must be filled!")
    private String password;
}
