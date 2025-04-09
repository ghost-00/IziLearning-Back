package com.jzi.izilearning.dto.user;

import lombok.Data;

import java.util.Set;

@Data
public class UserSignupDto {
  private String name;
  private String email;
  private String password;
  private Set<String> roles;
}
