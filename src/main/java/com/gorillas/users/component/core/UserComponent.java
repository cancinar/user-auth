package com.gorillas.users.component.core;

import com.gorillas.users.component.core.domain.UserDomain;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserComponent {

  Mono<UserDomain> createUser(UserDomain userDomain);

  Mono<UserDomain> findUser(String email, String password);

  Flux<UserDomain> findAll();
}
