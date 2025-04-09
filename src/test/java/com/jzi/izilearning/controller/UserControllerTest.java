package com.jzi.izilearning.controller;

import com.jzi.izilearning.config.DataInitializerConfig;
import com.jzi.izilearning.dto.user.UserLoginDto;
import com.jzi.izilearning.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Import(DataInitializerConfig.class)
class UserControllerTest {
  @Autowired
  private MockMvc mvc;
  @Autowired
  private AuthService authService;

  private String jwtToken;

  @BeforeEach
  public void setUp() {
    UserLoginDto userLoginDto = new UserLoginDto();
    userLoginDto.setEmail("john.doe@example.com");
    userLoginDto.setPassword("password");
    jwtToken = authService.login(userLoginDto);
  }

  @Test
  void testGetCurrentUser() throws Exception {
    mvc.perform(get("/api/users/me")
            .contentType("application/json")
            .header("Authorization", "Bearer " + jwtToken))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.firstname").value("John"))
            .andExpect(jsonPath("$.lastname").value("Doe"))
            .andExpect(jsonPath("$.username").value("john.doe"))
            .andExpect(jsonPath("$.email").value("john.doe@example.com"))
            .andExpect(jsonPath("$.roles[0].name").value("ADMIN"));
  }
}
