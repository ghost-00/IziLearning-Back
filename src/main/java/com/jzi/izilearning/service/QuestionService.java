package com.jzi.izilearning.service;

import com.jzi.izilearning.dto.QuestionDto;
import com.jzi.izilearning.entity.QuestionEntity;
import com.jzi.izilearning.mapper.QuizMapper;
import com.jzi.izilearning.repository.QuestionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

  private final QuestionRepository repository;

  public QuestionService(QuestionRepository repository) {
    this.repository = repository;
  }

  @Transactional
  public List<QuestionDto> findAll() {
    List<QuestionDto> result = new ArrayList<>();
    for (QuestionEntity question : repository.findAll()) {
      QuestionDto dto = QuizMapper.INSTANCE.toDto(question);
      result.add(dto);
    }
    return result;
  }

  @Transactional
  public QuestionDto create(QuestionDto dto) {
    QuestionEntity entity = QuizMapper.INSTANCE.fromDto(dto);
    repository.save(entity);
    return dto;
  }
}
