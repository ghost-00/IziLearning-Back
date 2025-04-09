package com.jzi.izilearning.repository;

import com.jzi.izilearning.config.DataInitializerConfig;
import com.jzi.izilearning.entity.RoleEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(DataInitializerConfig.class)
class RoleRepositoryTest {

  @Autowired
  private RoleRepository roleRepository;

  @Test
  void testCorrectFindByName() {
    RoleEntity result = roleRepository.findByName("ADMIN");
    Assertions.assertNotNull(result);
    Assertions.assertEquals("ADMIN", result.getName());
  }

  @Test
  void testIncorrectFindByName() {
    RoleEntity result = roleRepository.findByName("INCORRECT");
    Assertions.assertNull(result);
  }
}
