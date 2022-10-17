package com.app.src.auth.domain.gateways;

import com.app.src.auth.domain.user.User;
import reactor.core.publisher.Mono;

public interface UserSearchRepository {

    Mono<User> findByEmail(User user);
}
