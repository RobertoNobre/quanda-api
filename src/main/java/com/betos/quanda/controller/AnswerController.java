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

import com.betos.quanda.exception.ResourceNotFoundException;
import com.betos.quanda.model.Answer;
import com.betos.quanda.repository.AnswerRepository;
import com.betos.quanda.repository.QuestionRepository;

@RestController
public class AnswerController {

	@Autowired
	private AnswerRepository answerRepository;
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@GetMapping("/question/{questionId}/answer")
	public List<Answer> getAnswerbyQuestionId(@PathVariable Long questionId) {
		return answerRepository.findByQuestionId(questionId);
	}
	
	@PostMapping("/question/{questionId}/answer")
	public Answer create(@PathVariable Long questionId,
			@Valid @RequestBody Answer answer) {
		return questionRepository.findById(questionId)
				.map(question -> {
					answer.setQuestion(question);
					return answerRepository.save(answer);
				}).orElseThrow(() -> new ResourceNotFoundException("Question not found with id" + questionId));
	}
	
	@PutMapping("/question/{questionId}/answer/{answerId}")
	public Answer put(@PathVariable Long questionId,
			@PathVariable Long answerId, @Valid @RequestBody Answer answerRequest) {
		if(!questionRepository.existsById(questionId)) {
			throw new ResourceNotFoundException("Question not found with id" + questionId);
		}
		
		return answerRepository.findById(answerId)
				.map(answer -> {
					answer.setText(answerRequest.getText());
					return answerRepository.save(answer);
				}).orElseThrow(() -> new ResourceNotFoundException("Answer not found with id" + answerId));
	}
	
	@DeleteMapping("/question/{questionId}/answer/{answerId}")
	public ResponseEntity<?> delete(@PathVariable Long answerId, @PathVariable Long questionId) {
		if(!questionRepository.existsById(questionId)) {
			throw new ResourceNotFoundException("Question not found with id"+ questionId);
		}
		
		return answerRepository.findById(answerId)
				.map(answer -> {
					answerRepository.delete(answer);
					return ResponseEntity.ok().build();
				}).orElseThrow(() -> new ResourceNotFoundException("Answer not found with id" + answerId));
	}
}
