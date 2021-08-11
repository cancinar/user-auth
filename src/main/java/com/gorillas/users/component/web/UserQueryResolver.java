package com.gorillas.users.component.web;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.gorillas.users.component.core.UserComponent;
import com.gorillas.users.component.web.dto.UserDto;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserQueryResolver implements GraphQLQueryResolver {

  private final UserComponent userComponent;

  public CompletableFuture<List<UserDto>> users() {
    return userComponent.findAll()
        .map(UserDto::fromUserDomain)
        .collectList()
        .toFuture();
  }
}
