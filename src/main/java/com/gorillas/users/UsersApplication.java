package com.gorillas.users;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gorillas.users.component.core.UserComponent;
import com.gorillas.users.component.core.domain.UserDomain;
import com.gorillas.users.component.core.entity.User;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.config.EnableWebFlux;
import reactor.core.publisher.Mono;

@Slf4j
@EnableWebFlux
@SpringBootApplication(exclude = {WebMvcAutoConfiguration.class})
public class UsersApplication {

  public static void main(String[] args) {
    SpringApplication.run(UsersApplication.class, args);
  }

  @Bean
  CommandLineRunner runner(UserComponent userComponent) {
    return args -> {
      ObjectMapper mapper = new ObjectMapper();
      TypeReference<List<User>> typeReference = new TypeReference<List<User>>() {
      };
      InputStream inputStream = TypeReference.class.getResourceAsStream("/users.json");
      try {
        List<User> users = mapper.readValue(inputStream, typeReference);
        for (User user : users) {
          Mono<UserDomain> userDomain = userComponent.createUser(UserDomain.fromUser(user));
          log.info(
              "User is created: " + Objects.requireNonNull(userDomain.block()).getFirstName());
        }
      } catch (IOException e) {
        log.error("Unable to create user!", e);
      }
    };
  }
}
