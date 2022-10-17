package com.app.src.auth.domain.model;

import com.app.src.auth.domain.valueObjects.Email;
import com.app.src.auth.domain.valueObjects.Password;
import lombok.Data;

@Data
public class User {

    private String uid;
    private String name;
    private Email email;
    private Password password;
    private String image;
    private boolean google;
}
