package com.jzi.izilearning.controller;

import com.jzi.izilearning.dto.user.UserLoginDto;
import com.jzi.izilearning.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

  private final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }


  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody UserLoginDto userLoginDto) {
    try {
      String jwtToken = authService.login(userLoginDto);
      return new ResponseEntity<>(jwtToken, HttpStatus.OK);
    }
    catch (BadCredentialsException e){
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }
  }
}