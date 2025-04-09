package com.jzi.izilearning.service;

import com.jzi.izilearning.dto.user.UserDto;
import com.jzi.izilearning.entity.UserEntity;
import com.jzi.izilearning.mapper.QuizMapper;
import com.jzi.izilearning.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public UserDto getUserByEmail(String userEmail) {
    Optional<UserEntity> userEntity = userRepository.findByEmail(userEmail);
    if (userEntity.isPresent()) {
      UserDto userDto;
      userDto = QuizMapper.INSTANCE.toDto(userEntity.get());
      return userDto;
    }
    return null;
  }
}
