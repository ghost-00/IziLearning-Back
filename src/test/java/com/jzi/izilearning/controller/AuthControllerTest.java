package com.jzi.izilearning.controller;

import com.jzi.izilearning.config.DataInitializerConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Import(DataInitializerConfig.class)
class AuthControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void testLoginCorrectCredential() throws Exception {
    mockMvc.perform(post("/api/auth/login")
                    .contentType("application/json")
                    .content("{\"email\":\"john.doe@example.com\",\"password\":\"password\"}"))
            .andExpect(status().isOk());
  }

  @Test
  void testLoginWrongCredential() throws Exception {
    mockMvc.perform(post("/api/auth/login")
                    .contentType("application/json")
                    .content("{\"email\":\"john.doe@example.com\",\"password\":\"wrong password\"}"))
            .andExpect(status().isUnauthorized())
            .andExpect(content().string("Invalid credentials"));
  }
}
