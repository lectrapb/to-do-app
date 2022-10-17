package com.app.src.auth.application.singup;

import com.app.src.auth.domain.SignUpReqMother;
import com.app.src.auth.domain.gateways.PasswordEncryptService;
import com.app.src.auth.domain.gateways.UserSearchRepository;
import com.app.src.auth.domain.gateways.UserSignInRepository;
import com.app.src.auth.domain.model.User;
import com.app.src.auth.domain.util.UserMother;
import com.app.src.shared.domain.exception.BusinessException;
import com.app.src.shared.domain.util.Constant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
class SignUpUseCaseTest {



    private UserSignInRepository singInRepository;
    private UserSearchRepository searchRepository;
    private PasswordEncryptService encryptService;
    private SignUpUseCase useCase;

    @BeforeEach
    void setUp() {
        singInRepository = mock(UserSignInRepository.class);
        searchRepository = mock(UserSearchRepository.class);
        encryptService   = mock(PasswordEncryptService.class);
        useCase = new SignUpUseCase(singInRepository, searchRepository, encryptService);
    }

    @Test
    void signIn_use_case_error_null_test(){

         useCase.singUp(null)
                 .as(StepVerifier::create)
                 .expectErrorMatches(err -> err instanceof BusinessException &&
                         err.getMessage().equals(Constant.ERROR_MISSING_ARGUMENTS_CODE))
                 .verify();

    }

    @Test
    void signIn_use_case_error_user_exist_test(){

         when(searchRepository.findByEmail(any(User.class))).thenReturn(Mono.just(UserMother.ok()));

         useCase.singUp(SignUpReqMother.reqOk())
                 .as(StepVerifier::create)
                 .expectErrorMatches(err -> err instanceof  BusinessException
                         && err.getMessage().equals(Constant.ERROR_SIGNUP_USER_CODE))
                 .verify();
    }

    @Test
    void singIn_user_case_error_password_wrong_test(){

       useCase.singUp(SignUpReqMother.wrongPass())
               .as(StepVerifier::create)
               .expectErrorMatches(err -> err instanceof  BusinessException &&
                       err.getMessage().equals(Constant.ERROR_PASSWORD_FORMAT_CODE))
               .verify();
    }

    @Test
    void singIn_user_case_error_email_wrong_test(){

        useCase.singUp(SignUpReqMother.wrongEmail())
                .as(StepVerifier::create)
                .expectErrorMatches(err -> err instanceof  BusinessException &&
                        err.getMessage().equals(Constant.ERROR_EMAIL_FORMAT_CODE))
                .verify();
    }

    @Test
    void singIn_user_case_ok_test(){

        when(singInRepository.save(any())).thenReturn(Mono.just(UserMother.ok()));
        when(searchRepository.findByEmail(any(User.class))).thenReturn(Mono.empty());

        useCase.singUp(SignUpReqMother.reqOk())
                .as(StepVerifier::create)
                .consumeNextWith( dto ->{
                    assertNotNull(dto);
                })
                .verifyComplete();
    }
}