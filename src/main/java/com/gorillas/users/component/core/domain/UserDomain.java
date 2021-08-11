package com.gorillas.users.component.core.domain;

import com.gorillas.users.component.core.entity.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDomain {

  private Long id;
  private String firstName;
  private String lastName;
  private String userName;
  private String email;
  private String password;

  public static UserDomain fromUser(User user) {
    return UserDomain.builder()
        .firstName(user.getFirstName())
        .lastName(user.getLastName())
        .userName(user.getUserName())
        .password(user.getPassword())
        .email(user.getEmail())
        .build();
  }

  public User toNewUser() {
    return User.builder()
        .firstName(this.getFirstName())
        .lastName(this.getLastName())
        .email(this.getEmail())
        .password(this.getPassword())
        .userName(this.getUserName())
        .build();
  }
}
