package com.jzi.izilearning.dto.user;

import com.jzi.izilearning.dto.RoleDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
  private String firstname;
  private String lastname;
  private String username;
  private String email;
  private Set<RoleDto> roles;
  private String avatarUrl;
}
