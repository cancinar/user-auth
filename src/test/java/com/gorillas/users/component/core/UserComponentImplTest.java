package com.gorillas.users.component.core;

import static com.gorillas.users.generator.BaseGenerator.randomList;
import static com.gorillas.users.generator.UserDomainGenerator.randomUserDomain;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.gorillas.users.component.core.domain.UserDomain;
import com.gorillas.users.component.core.entity.User;
import com.gorillas.users.component.exception.ValidationException;
import com.gorillas.users.generator.UserDomainGenerator;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
public class UserComponentImplTest {

  private UserComponentImpl userComponent;

  @Mock
  private UserJPARepository userJPARepository;

  @BeforeEach
  public void init() {
    userComponent = new UserComponentImpl(userJPARepository);
  }

  @Test
  public void createUser_givenUser_whenValid_thenCreate() {
    UserDomain userDomain = randomUserDomain();
    User user = userDomain.toNewUser();

    when(userJPARepository.save(any(User.class))).thenReturn(Mono.just(user));

    final Mono<UserDomain> response = userComponent.createUser(userDomain);

    StepVerifier.create(response.log()).expectNext(userDomain).verifyComplete();
  }

  @Test
  public void createUser_givenUser_whenEmailInvalid_thenThrowException() {
    UserDomain userDomain = randomUserDomain();
    userDomain.setEmail(null);

    Exception exception = assertThrows(ValidationException.class,
        () -> userComponent.createUser(userDomain));
    assertEquals("Missing required fields.", exception.getMessage());

  }

  @Test
  public void createUser_givenUser_whenUserNameInvalid_thenThrowException() {
    UserDomain userDomain = randomUserDomain();
    userDomain.setUserName(null);

    Exception exception = assertThrows(ValidationException.class,
        () -> userComponent.createUser(userDomain));
    assertEquals("Missing required fields.", exception.getMessage());
  }

  @Test
  public void createUser_givenUser_whenFirstNameInvalid_thenThrowException() {
    UserDomain userDomain = randomUserDomain();
    userDomain.setFirstName(null);

    Exception exception = assertThrows(ValidationException.class,
        () -> userComponent.createUser(userDomain));
    assertEquals("Missing required fields.", exception.getMessage());
  }

  @Test
  public void createUser_givenUser_whenLastNameInvalid_thenThrowException() {
    UserDomain userDomain = randomUserDomain();
    userDomain.setLastName(null);

    Exception exception = assertThrows(ValidationException.class,
        () -> userComponent.createUser(userDomain));
    assertEquals("Missing required fields.", exception.getMessage());
  }

  @Test
  public void createUser_givenUser_whenPasswordInvalid_thenThrowException() {
    UserDomain userDomain = randomUserDomain();
    userDomain.setPassword(null);

    Exception exception = assertThrows(ValidationException.class,
        () -> userComponent.createUser(userDomain));
    assertEquals("Missing required fields.", exception.getMessage());
  }

  @Test
  public void findUser_givenUser_thenReturn() {
    UserDomain userDomain = randomUserDomain();

    when(userJPARepository
        .findByUserNameAndPassword(userDomain.getUserName(), userDomain.getPassword()))
        .thenReturn(Mono.just(userDomain.toNewUser()));

    final Mono<UserDomain> response = userComponent
        .findUser(userDomain.getUserName(), userDomain.getPassword());

    StepVerifier.create(response.log()).expectNext(userDomain).verifyComplete();
  }

  @Test
  public void findAllUser_givenUser_thenReturn() {
    List<UserDomain> userDomains = randomList(UserDomainGenerator::randomUserDomain);
    List<User> users = userDomains
        .stream()
        .map(UserDomain::toNewUser)
        .collect(Collectors.toList());

    when(userJPARepository
        .findAll())
        .thenReturn(Flux.fromIterable(users));

    final Flux<UserDomain> response = userComponent.findAll();

    StepVerifier.create(response)
        .recordWith(ArrayList::new)
        .thenConsumeWhile(x -> true)
        .expectRecordedMatches(userDomains::equals)
        .verifyComplete();
  }
}
