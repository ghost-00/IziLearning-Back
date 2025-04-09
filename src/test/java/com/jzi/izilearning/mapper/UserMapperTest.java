package com.jzi.izilearning.mapper;

import com.jzi.izilearning.dto.RoleDto;
import com.jzi.izilearning.dto.user.UserDto;
import com.jzi.izilearning.entity.RoleEntity;
import com.jzi.izilearning.entity.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@SpringBootTest
class UserMapperTest {

  @Test
  void testUserEntityToDto(){
    UserEntity userEntity = getUserEntity();
    UserDto dto = QuizMapper.INSTANCE.toDto(userEntity);

    Assertions.assertNotNull(dto);
    Assertions.assertEquals(userEntity.getFirstname(), dto.getFirstname());
    Assertions.assertEquals(userEntity.getLastname(), dto.getLastname());
    Assertions.assertEquals(userEntity.getEmail(), dto.getEmail());
    Assertions.assertEquals(userEntity.getUsername(), dto.getUsername());
    Assertions.assertEquals(extractRoleNames(userEntity.getRoles()), extractRoleNames(dto.getRoles()));
  }

  @Test
  void testUserDtoToEntity(){
    UserDto userDto = getUserDto();
    UserEntity entity = QuizMapper.INSTANCE.fromDto(userDto);

    Assertions.assertNotNull(entity);
    Assertions.assertEquals(userDto.getFirstname(), entity.getFirstname());
    Assertions.assertEquals(userDto.getLastname(), entity.getLastname());
    Assertions.assertEquals(userDto.getEmail(), entity.getEmail());
    Assertions.assertEquals(userDto.getUsername(), entity.getUsername());
    Assertions.assertEquals(extractRoleNames(userDto.getRoles()), extractRoleNames(entity.getRoles()));
  }

  private static UserEntity getUserEntity(){
    UserEntity userEntity = new UserEntity();
    userEntity.setFirstname("John");
    userEntity.setLastname("Doe");
    userEntity.setUsername("john.doe");
    userEntity.setEmail("john.doe@example.com");
    userEntity.setPassword("password");

    RoleEntity roleEntity1 = new RoleEntity();
    roleEntity1.setName("ADMIN");
    RoleEntity roleEntity2 = new RoleEntity();
    roleEntity2.setName("STAFF");
    Set<RoleEntity> roleEntities = new HashSet<>();
    roleEntities.add(roleEntity1);
    roleEntities.add(roleEntity2);
    userEntity.setRoles(roleEntities);

    return userEntity;
  }

  private static UserDto getUserDto(){
    UserDto userDto = new UserDto();
    userDto.setFirstname("John");
    userDto.setLastname("Doe");
    userDto.setUsername("john.doe");
    userDto.setEmail("john.doe@example.com");

    RoleDto roleDto1 = new RoleDto();
    roleDto1.setName("ADMIN");
    RoleDto roleDto2 = new RoleDto();
    roleDto2.setName("STAFF");
    Set<RoleDto> roleDtos = new HashSet<>();
    roleDtos.add(roleDto1);
    roleDtos.add(roleDto2);
    userDto.setRoles(roleDtos);

    return userDto;
  }

  private static Set<String> extractRoleNames(Set<?> roles) {
    return roles.stream()
            .map(role -> {
              if (role instanceof RoleEntity) return ((RoleEntity) role).getName().trim().toLowerCase();
              if (role instanceof RoleDto) return ((RoleDto) role).getName().trim().toLowerCase();
              return "";
            })
            .collect(Collectors.toSet());
  }
}
