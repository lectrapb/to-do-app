package com.app.src.auth.application.signin;

import static com.app.src.auth.domain.SignInReqMother.*;

import com.app.src.auth.domain.gateways.TokenService;
import com.app.src.auth.domain.gateways.UserSearchRepository;
import com.app.src.auth.domain.util.UserMother;
import com.app.src.shared.domain.exception.BusinessException;
import com.app.src.shared.domain.util.Constant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SignInUseCaseTest {

    private UserSearchRepository searchRepository;

    private TokenService tokenService;
    private SignInUseCase useCase;

    @BeforeEach
    void setUp() {
        searchRepository = mock(UserSearchRepository.class);
        tokenService = mock(TokenService.class);
        useCase = new SignInUseCase(searchRepository, tokenService);
    }

    @Test
    void signIn_use_case_null_test(){
        useCase.sigIn(null)
                .as(StepVerifier::create)
                .expectErrorMatches(err -> err instanceof BusinessException
                && err.getMessage().equals(Constant.ERROR_MISSING_ARGUMENTS_CODE))
                .verify();
    }

    @Test
    void signIn_use_case_wrong_email_test(){

        useCase.sigIn(wrongEmail())
                .as(StepVerifier::create)
                .expectErrorMatches(err -> err instanceof BusinessException
                        && err.getMessage().equals(Constant.ERROR_EMAIL_FORMAT_CODE))
                .verify();

    }

    @Test
    void signIn_use_case_wrong_password_test(){

        useCase.sigIn(wrongPass())
                .as(StepVerifier::create)
                .expectErrorMatches(err -> err instanceof BusinessException
                        && err.getMessage().equals(Constant.ERROR_PASSWORD_FORMAT_CODE))
                .verify();

    }

    @Test
    void signIn_use_case_ok_test(){

        when(searchRepository.findByEmail(any(String.class))).thenReturn(Mono.just(UserMother.ok()));
        when(tokenService.create(any())).thenReturn("");

        useCase.sigIn(okReq())
                .as(StepVerifier::create)
                .consumeNextWith(Assertions::assertNotNull)
                .verifyComplete();
    }


}