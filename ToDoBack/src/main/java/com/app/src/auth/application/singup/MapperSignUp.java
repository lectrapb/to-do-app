package com.app.src.auth.application.singup;

import com.app.src.auth.domain.dto.SignUpRequestDTO;
import com.app.src.auth.domain.valueObjects.Email;
import com.app.src.auth.domain.valueObjects.Password;
import com.app.src.auth.domain.model.User;

public class MapperSignUp {
    public static User toUser(SignUpRequestDTO requestDTO) {
         User user = new User();
         user.setUid(requestDTO.getUid());
         user.setName(requestDTO.getName());
         user.setEmail(new Email(requestDTO.getEmail()));
         user.setPassword(new Password(requestDTO.getPassword()));
         user.setImage(requestDTO.getImage());
         user.setGoogle(false);

         return user;
    }
}
