package com.gorillas.users.component.web;

import static com.gorillas.users.generator.BaseGenerator.randomList;
import static com.gorillas.users.generator.UserDomainGenerator.randomUserDomain;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.gorillas.users.component.core.UserComponent;
import com.gorillas.users.component.core.domain.UserDomain;
import com.gorillas.users.component.web.dto.UserAuthDto;
import com.gorillas.users.component.web.dto.UserDto;
import com.gorillas.users.generator.UserDomainGenerator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
public class UserQueryResolverTest {

  private UserQueryResolver userQueryResolver;

  @Mock
  private UserComponent userComponent;

  @BeforeEach
  private void init() {
    userQueryResolver = new UserQueryResolver(userComponent);
  }

  @Test
  public void users_givenUsers_thenReturn() {
    List<UserDomain> userDomains = randomList(UserDomainGenerator::randomUserDomain);

    when(userComponent.findAll())
        .thenReturn(Flux.fromIterable(userDomains));

    List<UserDto> userDtoList = userDomains
        .stream()
        .map(UserDto::fromUserDomain)
        .collect(Collectors.toList());

    assertEquals(userDtoList, userQueryResolver.users().join());
  }
}
