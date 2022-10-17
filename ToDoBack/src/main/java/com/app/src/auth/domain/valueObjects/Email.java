package com.app.src.auth.domain.valueObjects;

import com.app.src.auth.shared.util.ConstantAuth;
import com.app.src.shared.domain.exception.BusinessException;
import com.app.src.shared.domain.util.Constant;

public class Email implements ValueObjectStr {

    private String email;

    public Email(String email) {
        if( email == null){
            throw new BusinessException(Constant.ERROR_MISSING_ARGUMENTS_CODE);
        }else if(!ConstantAuth.PATTERN_MAIL.matcher(email).find()){
            throw new BusinessException(Constant.ERROR_EMAIL_FORMAT_CODE);
        }
        this.email = email;
    }

    @Override
    public String value() {
        return email;
    }
}
