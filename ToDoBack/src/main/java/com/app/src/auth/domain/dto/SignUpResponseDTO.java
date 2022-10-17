package com.app.src.auth.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignUpResponseDTO {

    private String uid;
    private String name;
    private String email;
    private String image;
    private boolean google;
}
