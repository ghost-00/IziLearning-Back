package com.jzi.izilearning.service;

import com.jzi.izilearning.dto.user.UserLoginDto;
import com.jzi.izilearning.utils.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

  private final JwtUtil jwtUtil;
  private final AuthenticationManager authenticationManager;

  public AuthService(JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
    this.jwtUtil = jwtUtil;
    this.authenticationManager = authenticationManager;
  }

  public String login(UserLoginDto userLoginDto) {
    // Authenticate the user
    Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginDto.getEmail(), userLoginDto.getPassword()));

    if (authentication == null || !authentication.isAuthenticated()) {
      return null;
    }

    // Set the authentication in the security context
    SecurityContextHolder.getContext().setAuthentication(authentication);
    // Generate JWT Token
    return jwtUtil.generateJwtToken(userLoginDto.getEmail());
  }
}
