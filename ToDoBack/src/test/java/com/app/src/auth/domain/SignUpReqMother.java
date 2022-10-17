package com.app.src.auth.domain;

import com.app.src.auth.domain.dto.SignUpRequestDTO;
import com.app.src.auth.domain.util.ConstantAuthTest;

public class SignUpReqMother {

    public static SignUpRequestDTO reqOk(){
        SignUpRequestDTO requestDTO = new SignUpRequestDTO();
        requestDTO.setEmail(ConstantAuthTest.EMAIL);
        requestDTO.setPassword(ConstantAuthTest.PASSWORD);
        return requestDTO;
    }

    public static SignUpRequestDTO wrongPass(){
        SignUpRequestDTO requestDTO = new SignUpRequestDTO();
        requestDTO.setEmail(ConstantAuthTest.EMAIL);
        requestDTO.setPassword(ConstantAuthTest.PASSWORD_WRONG);
        return requestDTO;
    }

    public static SignUpRequestDTO wrongEmail() {
        SignUpRequestDTO requestDTO = new SignUpRequestDTO();
        requestDTO.setEmail(ConstantAuthTest.EMAIL_WRONG);
        requestDTO.setPassword(ConstantAuthTest.PASSWORD);
        return requestDTO;
    }


}
