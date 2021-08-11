package com.gorillas.users.component.web;

import static com.gorillas.users.generator.UserDomainGenerator.randomUserDomain;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.gorillas.users.component.core.UserComponent;
import com.gorillas.users.component.core.domain.UserDomain;
import com.gorillas.users.component.web.dto.UserAuthDto;
import java.util.concurrent.CompletableFuture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
public class UserMutationResolverTest {

  private UserMutationResolver userMutationResolver;

  @Mock
  private UserComponent userComponent;

  @BeforeEach
  private void init() {
    userMutationResolver = new UserMutationResolver(userComponent);
  }

  @Test
  public void authorize_givenUser_thenReturnCode() {
    UserDomain userDomain = randomUserDomain();

    when(userComponent.findUser(userDomain.getUserName(), userDomain.getPassword()))
        .thenReturn(Mono.just(userDomain));

    assertNotNull(
        userMutationResolver.authorize(userDomain.getUserName(), userDomain.getPassword()));
  }

  @Test
  public void authorize_whenUserNotExist_thenThrowException() {
    UserDomain userDomain = randomUserDomain();

    when(userComponent.findUser(userDomain.getUserName(), userDomain.getPassword()))
        .thenReturn(Mono.empty());

    CompletableFuture<UserAuthDto> response = userMutationResolver
        .authorize(userDomain.getUserName(), userDomain.getPassword());

    assertTrue(response.isCompletedExceptionally());
  }
}
