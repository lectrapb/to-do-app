package com.app.src.auth.domain;

import com.app.src.auth.domain.dto.SignInRequestDTO;
import com.app.src.auth.domain.util.ConstantAuthTest;

public class SignInReqMother {

    public static SignInRequestDTO wrongEmail() {
        SignInRequestDTO requestDTO = new SignInRequestDTO();
        requestDTO.setEmail(ConstantAuthTest.EMAIL_WRONG);
        requestDTO.setPassword(ConstantAuthTest.PASSWORD);
        return requestDTO;
    }

    public static SignInRequestDTO wrongPass() {
        SignInRequestDTO requestDTO = new SignInRequestDTO();
        requestDTO.setEmail(ConstantAuthTest.EMAIL);
        requestDTO.setPassword(ConstantAuthTest.PASSWORD_WRONG);
        return requestDTO;
    }

    public static SignInRequestDTO okReq() {
        SignInRequestDTO requestDTO = new SignInRequestDTO();
        requestDTO.setEmail(ConstantAuthTest.EMAIL);
        requestDTO.setPassword(ConstantAuthTest.PASSWORD);
        return requestDTO;
    }
}
