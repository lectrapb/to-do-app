package com.app.src.auth.application.signin;

import com.app.src.auth.domain.dto.SignInRequestDTO;
import com.app.src.auth.domain.model.UserSignIn;
import com.app.src.auth.domain.valueObjects.Email;
import com.app.src.auth.domain.valueObjects.Password;

public class MapperSingIn {

    public static UserSignIn toModel(SignInRequestDTO requestDTO){

        UserSignIn signIn = new UserSignIn();
        signIn.setPassword(new Password(requestDTO.getPassword()));
        signIn.setEmail(new Email(requestDTO.getEmail()));
        return signIn;
    }
}
