package com.betos.quanda.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.betos.quanda.model.Question;
import com.betos.quanda.service.QuestionService;

@RestController
public class QuestionController {
	
	@Autowired
	private QuestionService questionService;
	
	@GetMapping("/question")
	public Page<Question> findAllPaged(Pageable pageable) {
		
		return questionService.findAll(pageable);
	}
	 
	@PostMapping("/question")
	public Question create(@Valid @RequestBody Question question) {
		
		return questionService.insert(question);
	}
	
	@PutMapping("/question/{questionId}")
	public Question update(@PathVariable Long questionId, @Valid @RequestBody Question questionRequest) {
		
		return questionService.update(questionId, questionRequest);
	}
	
	@DeleteMapping("/question/{questionId}")
	public ResponseEntity<?> delete(@PathVariable Long questionId) {
		
		return questionService.delete(questionId);
	}
}
