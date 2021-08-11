package com.gorillas.users.generator;

import static com.gorillas.users.generator.BaseGenerator.randomEmail;
import static com.gorillas.users.generator.BaseGenerator.randomFirstName;
import static com.gorillas.users.generator.BaseGenerator.randomId;
import static com.gorillas.users.generator.BaseGenerator.randomLastName;
import static com.gorillas.users.generator.BaseGenerator.randomPassword;
import static com.gorillas.users.generator.BaseGenerator.randomUserName;

import com.gorillas.users.component.core.entity.User;

public class UserGenerator {

  public static User randomUser() {
    return User.builder()
        .id(randomId())
        .userName(randomUserName())
        .firstName(randomFirstName())
        .lastName(randomLastName())
        .email(randomEmail())
        .password(randomPassword())
        .build();
  }
}
