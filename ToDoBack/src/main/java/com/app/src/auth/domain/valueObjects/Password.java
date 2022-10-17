package com.app.src.auth.domain.valueObjects;

import com.app.src.auth.shared.util.ConstantAuth;
import com.app.src.shared.domain.exception.BusinessException;
import com.app.src.shared.domain.util.Constant;

public class Password implements ValueObjectStr {

    private String password;

    public Password(String password) {
        if(password == null){
            throw new BusinessException(Constant.ERROR_MISSING_ARGUMENTS_CODE);
        } else if (password.length() < ConstantAuth.PARAM_MIN_LENGTH_PASS) {
            throw  new BusinessException(Constant.ERROR_PASSWORD_FORMAT_CODE);
        }
        this.password = password;
    }

    @Override
    public String value() {
        return password;
    }
}
