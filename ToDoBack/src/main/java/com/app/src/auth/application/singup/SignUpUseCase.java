package com.app.src.auth.application.singup;

import com.app.src.auth.domain.dto.SignUpRequestDTO;
import com.app.src.auth.domain.dto.SignUpResponseDTO;
import com.app.src.auth.domain.gateways.PasswordEncryptService;
import com.app.src.auth.domain.gateways.UserSearchRepository;
import com.app.src.auth.domain.gateways.UserSignInRepository;
import com.app.src.auth.domain.model.User;
import com.app.src.shared.domain.exception.BusinessException;
import com.app.src.shared.domain.util.Constant;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;


@AllArgsConstructor
public class SignUpUseCase {

      private final UserSignInRepository signInRepository;
      private final UserSearchRepository searchRepository;

      private final PasswordEncryptService encryptService;

    public Mono<SignUpResponseDTO> singUp(SignUpRequestDTO requestDTO){

          return  Mono.fromCallable(() -> requestDTO)
                   .switchIfEmpty(Mono.error(new BusinessException(Constant.ERROR_MISSING_ARGUMENTS_CODE)))
                   .map( MapperSignUp::toUser)
                   .flatMap(this::verifyEmail)
                   .map(user -> {
                     user.setPassword(encryptService.encryptPwd(user.getPassword().value()));
                     return user ;
                    })
                   .flatMap(signInRepository::save)
                   .map(this::prepareOkResponse);
      }


   private Mono<User> verifyEmail (User user){
         return  Mono.fromCallable( () -> user)
                 .flatMap(searchRepository::findByEmail)
                 .map(this::ifExist)
                 .defaultIfEmpty(user);
   }
    private User ifExist(User user){
        if(user != null){
            throw new BusinessException(Constant.ERROR_SIGNUP_USER_CODE);
        }
        return user;
    }

    private SignUpResponseDTO prepareOkResponse(User user) {

          return   SignUpResponseDTO.builder()
                  .uid(user.getUid())
                  .name(user.getName())
                  .email(user.getEmail().value())
                  .image(user.getImage())
                  .google(user.isGoogle())
                  .build();
    }
}
