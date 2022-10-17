package com.app.src.auth.domain.gateways;

import com.app.src.auth.domain.valueObjects.Password;

public interface PasswordEncryptService {

    Password encryptPwd(String password);
}
