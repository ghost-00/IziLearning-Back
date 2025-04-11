package com.jzi.izilearning.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name="questions")
@Getter @Setter @NoArgsConstructor
public class QuestionEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(unique = true, nullable = false)
  private Long id;
  @Column(name = "question_text")
  private String questionText;
  private String hint;

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<ChoiceEntity> choices;
}
