package com.app.src.auth.domain.model;

import com.app.src.auth.domain.valueObjects.Email;
import com.app.src.auth.domain.valueObjects.Password;
import lombok.Data;

@Data
public class UserSignIn {

    private Email email;
    private Password password;

    public UserSignIn() {
    }

    public UserSignIn(Email email, Password password) {
        this.email = email;
        this.password = password;
    }
}
