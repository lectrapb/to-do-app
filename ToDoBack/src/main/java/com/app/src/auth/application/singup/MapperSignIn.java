package com.app.src.auth.application.singup;

import com.app.src.auth.domain.dto.SingUpRequestDTO;
import com.app.src.auth.domain.user.User;
import com.app.src.shared.domain.util.Constant;

public class MapperSignIn {
    public static User toUser(SingUpRequestDTO requestDTO) {
         User user = new User();
         user.setUid(requestDTO.getUid());
         user.setName(requestDTO.getName());
         user.setEmail(requestDTO.getEmail());
         user.setPassword(requestDTO.getPassword());
         user.setImage(requestDTO.getImage());
         String role = requestDTO.getRole() != null ?
                 requestDTO.getRole():
                 Constant.ROLE_USER;
         user.setRole(role);
         user.setGoogle(false);

         return user;
    }
}
