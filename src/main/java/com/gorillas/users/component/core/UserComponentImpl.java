package com.gorillas.users.component.core;

import static org.apache.logging.log4j.util.Strings.isNotEmpty;

import com.gorillas.users.component.core.domain.UserDomain;
import com.gorillas.users.component.core.entity.User;
import com.gorillas.users.component.exception.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
class UserComponentImpl implements UserComponent {

  private final UserJPARepository userJPARepository;

  @Override
  public Mono<UserDomain> createUser(UserDomain userDomain) {
    if (isCreatable(userDomain)) {
      return userJPARepository.save(userDomain.toNewUser())
          .map(UserDomain::fromUser);
    }

    throw new ValidationException("Missing required fields.");
  }

  @Override
  public Mono<UserDomain> findUser(String userName, String password) {
    return userJPARepository.findByUserNameAndPassword(userName, password)
        .map(UserDomain::fromUser);
  }

  @Override
  public Flux<UserDomain> findAll() {
    return userJPARepository.findAll()
        .map(UserDomain::fromUser);
  }

  private boolean isCreatable(UserDomain userDomain) {
    return isNotEmpty(userDomain.getFirstName()) &
        isNotEmpty(userDomain.getLastName()) &
        isNotEmpty(userDomain.getEmail()) &
        isNotEmpty(userDomain.getUserName()) &&
        isNotEmpty(userDomain.getPassword());
  }
}
