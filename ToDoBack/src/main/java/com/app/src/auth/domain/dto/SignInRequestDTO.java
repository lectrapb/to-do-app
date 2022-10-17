package com.app.src.auth.domain.dto;

import lombok.Data;

@Data
public class SignInRequestDTO {

    private String email;
    private String password;
}
