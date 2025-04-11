package com.jzi.izilearning.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name="choices")
@Getter
@Setter
@NoArgsConstructor
public class ChoiceEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(unique = true, nullable = false)
  private long id;
  private String text;
  private Boolean isAnswer;

  @ManyToMany(mappedBy = "choices")
  private List<QuestionEntity> questions;
}
