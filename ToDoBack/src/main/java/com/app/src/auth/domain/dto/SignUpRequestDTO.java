package com.app.src.auth.domain.dto;

import lombok.Data;

@Data
public class SignUpRequestDTO {

    private String uid;
    private String name;
    private String email;
    private String password;
    private String image;
    private String role;
    private boolean google;
}
