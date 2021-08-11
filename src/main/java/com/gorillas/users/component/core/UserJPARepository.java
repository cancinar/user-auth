package com.gorillas.users.component.core;

import com.gorillas.users.component.core.entity.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

interface UserJPARepository extends ReactiveCrudRepository<User, Long> {

  Mono<User> findByUserNameAndPassword(String email, String password);
}
