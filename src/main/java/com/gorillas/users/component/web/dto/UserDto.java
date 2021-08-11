package com.gorillas.users.component.web.dto;

import com.gorillas.users.component.core.domain.UserDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserDto {

  private Long id;
  private String firstName;
  private String lastName;
  private String email;
  private String userName;

  public static UserDto fromUserDomain(UserDomain userDomain) {
    return UserDto.builder()
        .id(userDomain.getId())
        .firstName(userDomain.getFirstName())
        .lastName(userDomain.getLastName())
        .email(userDomain.getEmail())
        .userName(userDomain.getUserName())
        .build();
  }
}
