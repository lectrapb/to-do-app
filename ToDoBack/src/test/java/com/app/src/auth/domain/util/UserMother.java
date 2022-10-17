package com.app.src.auth.domain.util;

import com.app.src.auth.domain.model.User;
import com.app.src.auth.domain.valueObjects.Email;
import com.app.src.auth.domain.valueObjects.Password;

public class UserMother {

    public static User ok(){

        User userOk = new User();
        userOk.setEmail(new Email(ConstantAuthTest.EMAIL));
        userOk.setPassword(new Password(ConstantAuthTest.PASSWORD));
        return userOk;
    }
}
