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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Import(DataInitializerConfig.class)
class QuestionControllerTest {

  @Autowired
  private MockMvc mockMvc;

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
  void testGetAll() throws Exception {
    mockMvc.perform(get("/api/questions"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$").exists());
  }

  @Test
  void testCreateUnauthorized() throws Exception {
    mockMvc.perform(post("/api/questions/new")
                    .contentType("application/json")
                    .content("{" +
                            "\"hint\":\"hint test\", " +
                            "\"questionText\":\"question test\", " +
                            "\"choices\":[" +
                            "{\"text\":\"text1\",\"isAnswer\":\"True\"}, " +
                            "{\"text\":\"text2\",\"isAnswer\":\"False\"}]}"))
                    .andExpect(status().isForbidden());
  }

  @Test
  void testCreateAuthorized() throws Exception {
    mockMvc.perform(post("/api/questions/new")
                    .header("Authorization", "Bearer " + jwtToken)
                    .contentType("application/json")
                    .content("{" +
                            "\"hint\":\"hint test\", " +
                            "\"questionText\":\"question test\", " +
                            "\"choices\":[" +
                            "{\"text\":\"text1\",\"isAnswer\":\"True\"}, " +
                            "{\"text\":\"text2\",\"isAnswer\":\"False\"}]}"))
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.hint").value("hint test"))
            .andExpect(jsonPath("$.questionText").value("question test"))
            .andExpect(jsonPath("$.choices").isArray())
            .andExpect(jsonPath("$.choices[0].text").value("text1"))
            .andExpect(jsonPath("$.choices[0].isAnswer").value("true"))
            .andExpect(jsonPath("$.choices[1].text").value("text2"))
            .andExpect(jsonPath("$.choices[1].isAnswer").value("false"));
  }
}
