package com.app.src.auth.domain.gateways;

import com.app.src.auth.domain.model.User;
import reactor.core.publisher.Mono;

public interface UserSearchRepository {

    Mono<User> findByEmail(User user);

    Mono<User> findByEmail(String email);
}
