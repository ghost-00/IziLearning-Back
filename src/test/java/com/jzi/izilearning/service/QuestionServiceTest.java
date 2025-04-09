package com.jzi.izilearning.service;

import com.jzi.izilearning.config.DataInitializerConfig;
import com.jzi.izilearning.dto.ChoiceDto;
import com.jzi.izilearning.dto.QuestionDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@SpringBootTest
@Import(DataInitializerConfig.class)
class QuestionServiceTest {

  @Autowired
  private QuestionService questionService;

  @Test
  void testFindAll(){
    List<QuestionDto> questions = questionService.findAll();
    Assertions.assertNotNull(questions);
  }

  @Test
  void testCreateQuestion(){
    QuestionDto questionDto = new QuestionDto();
    questionDto.setQuestionText("Question test3");
    questionDto.setHint("Hint test3");
    ChoiceDto choiceDto = new ChoiceDto();
    choiceDto.setIsAnswer(true);
    choiceDto.setText("choice");

    List<QuestionDto> questionsBefore = questionService.findAll();

    QuestionDto question = questionService.create(questionDto);
    Assertions.assertNotNull(question);

    List<QuestionDto> questionsAfter = questionService.findAll();
    Assertions.assertEquals(questionsBefore.size()+1, questionsAfter.size());
  }
}
