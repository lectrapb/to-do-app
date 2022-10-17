package com.app.src.auth.application.singup;

import com.app.src.auth.domain.dto.SingUpRequestDTO;
import com.app.src.auth.domain.dto.SingUpResponseDTO;
import com.app.src.auth.domain.gateways.UserSearchRepository;
import com.app.src.auth.domain.gateways.UserSingInRepository;
import com.app.src.auth.domain.user.User;
import com.app.src.shared.domain.exception.BusinessException;
import com.app.src.shared.domain.util.Constant;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;


@AllArgsConstructor
public class SignUpUseCase {

      private final UserSingInRepository signInRepository;
      private final UserSearchRepository searchRepository;

    public Mono<SingUpResponseDTO> singUp(SingUpRequestDTO requestDTO){

          return  Mono.fromCallable(() -> requestDTO)
                   .switchIfEmpty(Mono.error(new BusinessException(Constant.ERROR_MISSING_ARGUMENTS_CODE)))
                   .map( MapperSignIn::toUser)
                   .flatMap(this::verifyEmail)
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

    private SingUpResponseDTO prepareOkResponse(User user) {

          return   SingUpResponseDTO.builder()
                  .uid(user.getUid())
                  .name(user.getName())
                  .email(user.getEmail())
                  .image(user.getImage())
                  .google(user.isGoogle())
                  .build();
    }
}
