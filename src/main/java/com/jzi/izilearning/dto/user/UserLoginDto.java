package com.jzi.izilearning.dto.user;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class UserLoginDto {
  @JsonProperty("email")
  private String email;
  @JsonProperty("password")
  private String password;
}
