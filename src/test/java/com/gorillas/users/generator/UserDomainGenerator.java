package com.gorillas.users.generator;

import static com.gorillas.users.generator.BaseGenerator.randomEmail;
import static com.gorillas.users.generator.BaseGenerator.randomFirstName;
import static com.gorillas.users.generator.BaseGenerator.randomId;
import static com.gorillas.users.generator.BaseGenerator.randomLastName;
import static com.gorillas.users.generator.BaseGenerator.randomPassword;
import static com.gorillas.users.generator.BaseGenerator.randomUserName;

import com.gorillas.users.component.core.domain.UserDomain;

public class UserDomainGenerator {

  public static UserDomain randomUserDomain() {
    return UserDomain.builder()
        .userName(randomUserName())
        .firstName(randomFirstName())
        .lastName(randomLastName())
        .email(randomEmail())
        .password(randomPassword())
        .build();
  }
}
