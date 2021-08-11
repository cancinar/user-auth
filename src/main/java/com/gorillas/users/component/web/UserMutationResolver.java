package com.gorillas.users.component.web;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.gorillas.users.component.core.UserComponent;
import com.gorillas.users.component.exception.UserNotFoundException;
import com.gorillas.users.component.web.dto.UserAuthDto;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class UserMutationResolver implements GraphQLMutationResolver {

  private final UserComponent userComponent;

  public CompletableFuture<UserAuthDto> authorize(String userName, String password) {
    return userComponent.findUser(userName, password)
        .switchIfEmpty(Mono.error(new UserNotFoundException("User not found!")))
        .map(userDomain -> new UserAuthDto(new Random().nextLong()))
        .toFuture();
  }
}
