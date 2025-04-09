package com.jzi.izilearning.mapper;

import com.jzi.izilearning.dto.ChoiceDto;
import com.jzi.izilearning.dto.QuestionDto;
import com.jzi.izilearning.dto.RoleDto;
import com.jzi.izilearning.dto.user.UserDto;
import com.jzi.izilearning.entity.ChoiceEntity;
import com.jzi.izilearning.entity.QuestionEntity;
import com.jzi.izilearning.entity.RoleEntity;
import com.jzi.izilearning.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Mapper
public interface QuizMapper {

  QuizMapper INSTANCE = Mappers.getMapper(QuizMapper.class);

  ChoiceDto toDto(ChoiceEntity choiceEntity);
  @Mapping(target="id", ignore = true)
  ChoiceEntity fromDto(ChoiceDto choiceDto);

  List<ChoiceDto> toDto(List<ChoiceEntity> choiceEntities);
  List<ChoiceEntity> fromDto(List<ChoiceDto> choiceDtos);

  QuestionDto toDto(QuestionEntity question);
  @Mapping(target = "id", ignore = true)
  QuestionEntity fromDto(QuestionDto questionDto);

  RoleDto toDto(RoleEntity roleEntity);
  @Mapping(target = "id", ignore = true)
  RoleEntity fromDto(RoleDto roleDto);

  Set<RoleDto> toDto(Set<RoleEntity> roleEntities);
  Set<RoleEntity> fromDto(Set<RoleDto> roleDtos);

  UserDto toDto(UserEntity user);
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "password", ignore = true)
  UserEntity fromDto(UserDto dto);

}
