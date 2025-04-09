package com.jzi.izilearning.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @NoArgsConstructor
public class QuestionDto {
  private long id;
  private String questionText;
  private List<ChoiceDto> choices;
  private String hint;
}
