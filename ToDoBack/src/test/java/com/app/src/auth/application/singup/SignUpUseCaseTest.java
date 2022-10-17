package com.app.src.auth.application.singup;

import com.app.src.auth.domain.dto.SingUpRequestDTO;
import com.app.src.auth.domain.gateways.UserSearchRepository;
import com.app.src.auth.domain.gateways.UserSingInRepository;
import com.app.src.auth.domain.user.User;
import com.app.src.shared.domain.exception.BusinessException;
import com.app.src.shared.domain.util.Constant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
class SignUpUseCaseTest {

    private UserSingInRepository singInRepository;
    private UserSearchRepository searchRepository;
    private SignUpUseCase useCase;

    @BeforeEach
    void setUp() {
        singInRepository = mock(UserSingInRepository.class);
        searchRepository = mock(UserSearchRepository.class);
        useCase = new SignUpUseCase(singInRepository, searchRepository);
    }

    @Test
    void signIn_use_case_null_test(){

         useCase.singUp(null)
                 .as(StepVerifier::create)
                 .expectErrorMatches(err -> err instanceof BusinessException &&
                         err.getMessage().equals(Constant.ERROR_MISSING_ARGUMENTS_CODE))
                 .verify();

    }

    @Test
    void signIn_use_case_user_exist_test(){


         when(searchRepository.findByEmail(any())).thenReturn(Mono.just(new User()));

         useCase.singUp(new SingUpRequestDTO())
                 .as(StepVerifier::create)
                 .expectErrorMatches(err -> err instanceof  BusinessException
                         && err.getMessage().equals(Constant.ERROR_SIGNUP_USER_CODE))
                 .verify();
    }

    @Test
    void singIn_user_case_ok_test(){

       when(singInRepository.save(any())).thenReturn(Mono.just(new User()));
       when(searchRepository.findByEmail(any())).thenReturn(Mono.empty());

       useCase.singUp(new SingUpRequestDTO())
               .as(StepVerifier::create)
               .consumeNextWith( dto ->{
                   assertNotNull(dto);
               })
               .verifyComplete();


    }
}