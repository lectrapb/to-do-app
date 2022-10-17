package com.app.src.auth.application.signin;

import com.app.src.auth.domain.dto.SignInRequestDTO;
import com.app.src.auth.domain.dto.SignInResponseDTO;
import com.app.src.auth.domain.gateways.TokenService;
import com.app.src.auth.domain.gateways.UserSearchRepository;
import com.app.src.auth.domain.model.User;
import com.app.src.shared.domain.exception.BusinessException;
import com.app.src.shared.domain.util.Constant;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicReference;

@AllArgsConstructor
public class SignInUseCase {

    private final UserSearchRepository searchRepository;

    private final TokenService tokenService;
    public Mono<SignInResponseDTO> sigIn(SignInRequestDTO requestDTO){

        AtomicReference<String> password = new AtomicReference<>("");
         return Mono.fromCallable(() -> requestDTO)
                .switchIfEmpty(Mono.error(new BusinessException(Constant.ERROR_MISSING_ARGUMENTS_CODE)))
                .map(MapperSingIn::toModel)
                .flatMap(userSignIn -> {
                    password.set(userSignIn.getPassword().value());
                    return searchRepository.findByEmail(userSignIn.getEmail().value());
                })
                .switchIfEmpty(Mono.error(new BusinessException(Constant.ERROR_LOGIN_USER_CODE)))
                .map(currentUser -> evaluatePassword(currentUser, password.get()));
    }

    private SignInResponseDTO  evaluatePassword (User user, String currentPassword){

        if(!user.getPassword().value().equals(currentPassword)){
            throw new BusinessException(Constant.ERROR_LOGIN_USER_CODE);
        }
        return SignInResponseDTO.builder()
                .uid(user.getUid())
                .name(user.getName())
                .token(tokenService.create(user.getUid()))
                .build();

    }
}
