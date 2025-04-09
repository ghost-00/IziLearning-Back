package com.jzi.izilearning.repository;

import com.jzi.izilearning.config.DataInitializerConfig;
import com.jzi.izilearning.dto.user.UserDto;
import com.jzi.izilearning.entity.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import java.util.Optional;

@SpringBootTest
@Import(DataInitializerConfig.class)
class UserRepositoryTest {

  @Autowired
  private UserRepository userRepository;
  UserDto userDto;

  @BeforeEach
  void setUp() {
    userDto = new UserDto();
  }

  @Test
  void testCorrectFindByEmail(){
    userDto.setEmail("john.doe@example.com");
    Optional<UserEntity> result = userRepository.findByEmail(userDto.getEmail());
    Assertions.assertTrue(result.isPresent());
    Assertions.assertEquals(userDto.getEmail(), result.get().getEmail());
  }

  @Test
  void testIncorrectFindByEmail(){
    userDto.setEmail("incorrect.doe@example.com");
    Optional<UserEntity> result = userRepository.findByEmail(userDto.getEmail());
    Assertions.assertFalse(result.isPresent());
  }

  @Test
  void testExistsByEmail(){
    userDto.setEmail("john.doe@example.com");
    Assertions.assertTrue(userRepository.existsByEmail(userDto.getEmail()));
  }

  @Test
  void testNotExistsByEmail(){
    userDto.setEmail("incorrect.doe@example.com");
    Assertions.assertFalse(userRepository.existsByEmail(userDto.getEmail()));
  }
}
