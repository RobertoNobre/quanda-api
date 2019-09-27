package com.betos.quanda.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.betos.quanda.model.Answer;
import com.betos.quanda.service.AnswerService;

@RestController
public class AnswerController {

	@Autowired
	private AnswerService answerService;
	
	@GetMapping("/question/{questionId}/answer")
	public List<Answer> getAnswerbyQuestionId(@PathVariable Long questionId) {
		return answerService.getAnswerbyQuestionId(questionId);
	}
	
	@PostMapping("/question/{questionId}/answer")
	public Answer create(@PathVariable Long questionId, @Valid @RequestBody Answer answer) {
		return answerService.create(questionId, answer);
	}
	
	@PutMapping("/question/{questionId}/answer/{answerId}")
	public Answer put(@PathVariable Long questionId,
			@PathVariable Long answerId, @Valid @RequestBody Answer answerRequest) {
		return answerService.put(questionId, answerId, answerRequest);
	}
	
	@DeleteMapping("/question/{questionId}/answer/{answerId}")
	public ResponseEntity<?> delete(@PathVariable Long answerId, @PathVariable Long questionId) {
		return answerService.delete(answerId, questionId);
	}
}
