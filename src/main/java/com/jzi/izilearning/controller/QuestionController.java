package com.jzi.izilearning.controller;

import com.jzi.izilearning.dto.QuestionDto;
import com.jzi.izilearning.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

  private final QuestionService service;

  public QuestionController(QuestionService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<List<QuestionDto>> getAll() {
    return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
  }

  @PostMapping("/new")
  public ResponseEntity<QuestionDto> create(@RequestBody QuestionDto questionDto) {
    return new ResponseEntity<>(this.service.create(questionDto), HttpStatus.CREATED);
  }
}