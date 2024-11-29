package com.example.demo.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AddUserRequest {
  private String email;
  private String password;
  private String name;
}
