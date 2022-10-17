package com.app.src.auth.domain.gateways;

import com.app.src.auth.domain.model.User;
import reactor.core.publisher.Mono;

public interface UserSignInRepository {
    Mono<User> save(User dto);
}
