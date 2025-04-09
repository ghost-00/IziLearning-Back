package com.jzi.izilearning.mapper;

import com.jzi.izilearning.dto.ChoiceDto;
import com.jzi.izilearning.dto.QuestionDto;
import com.jzi.izilearning.entity.ChoiceEntity;
import com.jzi.izilearning.entity.QuestionEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class QuestionMapperTest {

  @Test
  void testQuestionEntityToDto(){
    QuestionEntity questionEntity = getQuestionEntity();

    QuestionDto dto = QuizMapper.INSTANCE.toDto(questionEntity);

    Assertions.assertNotNull(dto);
    Assertions.assertEquals(questionEntity.getQuestionText(), dto.getQuestionText());
    Assertions.assertEquals(questionEntity.getHint(), dto.getHint());
    Assertions.assertEquals(questionEntity.getChoices().size(), dto.getChoices().size());
    Assertions.assertTrue(compareChoicesList(questionEntity.getChoices(), dto.getChoices()));
  }

  @Test
  void testQuestionDtoToEntity(){
    QuestionDto dto = getQuestionDto();

    QuestionEntity entity = QuizMapper.INSTANCE.fromDto(dto);
    Assertions.assertNotNull(entity);
    Assertions.assertEquals(dto.getQuestionText(), entity.getQuestionText());
    Assertions.assertEquals(dto.getHint(), entity.getHint());
    Assertions.assertEquals(dto.getChoices().size(), entity.getChoices().size());
    Assertions.assertTrue(compareChoicesList(entity.getChoices(), dto.getChoices()));
  }

  private static QuestionEntity getQuestionEntity() {
    QuestionEntity questionEntity = new QuestionEntity();
    questionEntity.setQuestionText("question text");
    questionEntity.setHint("question hint");

    // Choices
    ChoiceEntity choiceEntity1 = new ChoiceEntity();
    choiceEntity1.setIsAnswer(true);
    choiceEntity1.setText("choice1");
    ChoiceEntity choiceEntity2 = new ChoiceEntity();
    choiceEntity2.setIsAnswer(false);
    choiceEntity2.setText("choice2");
    List<ChoiceEntity> choiceEntityList = new ArrayList<>();
    choiceEntityList.add(choiceEntity1);
    choiceEntityList.add(choiceEntity2);
    questionEntity.setChoices(choiceEntityList);
    return questionEntity;
  }

  private static QuestionDto getQuestionDto() {
    QuestionDto questionDto = new QuestionDto();
    questionDto.setQuestionText("question text");
    questionDto.setHint("question hint");

    // Choices
    ChoiceDto choiceDto1 = new ChoiceDto();
    choiceDto1.setIsAnswer(true);
    choiceDto1.setText("choice1");
    ChoiceDto choiceDto2 = new ChoiceDto();
    choiceDto2.setIsAnswer(false);
    choiceDto2.setText("choice2");
    List<ChoiceDto> choiceDtoList = new ArrayList<>();
    choiceDtoList.add(choiceDto1);
    choiceDtoList.add(choiceDto2);
    questionDto.setChoices(choiceDtoList);
    return questionDto;
  }

  private static boolean compareChoicesList(List<ChoiceEntity> choiceEntities, List<ChoiceDto> choiceDtos) {
    for(ChoiceEntity choiceEntity : choiceEntities){
      for(ChoiceDto choiceDto : choiceDtos){
        if(choiceDto.getIsAnswer() != choiceEntity.getIsAnswer() && !choiceDto.getText().equals(choiceDto.getText())){
          return false;
        }
      }
    }
    return true;
  }
}
