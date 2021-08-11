package com.gorillas.users.component.core.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

  @Id
  @Column("ID")
  private Long id;
  @Column("FIRST_NAME")
  private String firstName;
  @Column("LAST_NAME")
  private String lastName;
  @Column("USER_NAME")
  private String userName;
  @Column("EMAIL")
  private String email;
  @Column("PASSWORD")
  private String password;
}
