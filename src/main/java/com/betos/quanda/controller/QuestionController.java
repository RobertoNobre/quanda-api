package com.betos.quanda.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.betos.quanda.model.Question;
import com.betos.quanda.repository.QuestionRepository;

@RestController
public class QuestionController {

	@Autowired
	private QuestionRepository questionRepository;
	
	@GetMapping("/question")
	public Page<Question> findAllPaged(Pageable pageable) {
		return questionRepository.findAll(pageable);
	}
	
	@PostMapping("/question")
	public Question create(@Valid @RequestBody Question question) {
		return questionRepository.save(question);
	}
}
