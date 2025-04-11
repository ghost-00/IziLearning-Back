package com.jzi.izilearning.service;

import com.jzi.izilearning.dto.user.UserDto;
import com.jzi.izilearning.entity.UserEntity;
import com.jzi.izilearning.mapper.QuizMapper;
import com.jzi.izilearning.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

  public UserDto updateUser(String email, UserDto updatedData) {
    UserEntity user = userRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    UserEntity updatedUser = QuizMapper.INSTANCE.fromDto(updatedData);

    user.setFirstname(updatedUser.getFirstname());
    user.setLastname(updatedUser.getLastname());
    user.setEmail(updatedUser.getEmail());
    user.setUsername(updatedUser.getUsername());
    user.setRoles(updatedUser.getRoles());
    user.setAvatarUrl(updatedUser.getAvatarUrl());
    userRepository.save(user);

    return QuizMapper.INSTANCE.toDto(user);
  }
}