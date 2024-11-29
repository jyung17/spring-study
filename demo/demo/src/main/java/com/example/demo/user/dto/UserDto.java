package com.example.demo.user.dto;

import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

  private Long id;
  private String email;
  private String password;
  private String name;

  @Builder
  public UserDto(String email, String password, String name, String auth) {
    this.email = email;
    this.password = password;
    this.name = name;
  }
}

