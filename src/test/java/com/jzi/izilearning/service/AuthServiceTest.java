package com.jzi.izilearning.service;

import com.jzi.izilearning.config.DataInitializerConfig;
import com.jzi.izilearning.dto.user.UserLoginDto;
import com.jzi.izilearning.utils.JwtUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.BadCredentialsException;


@SpringBootTest
@Import(DataInitializerConfig.class)
class AuthServiceTest {

  @Autowired
  private AuthService authService;

  @Test
  void testLoginAuthenticated(){
    // User login DTO
    UserLoginDto userLoginDto = new UserLoginDto();
    userLoginDto.setEmail("john.doe@example.com");
    userLoginDto.setPassword("password");

    String jwtToken = authService.login(userLoginDto);
    Assertions.assertNotNull(jwtToken);
  }

  @Test
  void testLoginNotAuthenticated(){
    UserLoginDto userLoginDto = new UserLoginDto();
    userLoginDto.setEmail("john.doe@example.com");
    userLoginDto.setPassword("wrong password");
    Assertions.assertThrows(BadCredentialsException.class, () -> authService.login(userLoginDto));
  }
}
