package com.betos.quanda.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.betos.quanda.exception.ResourceNotFoundException;
import com.betos.quanda.model.Answer;
import com.betos.quanda.repository.AnswerRepository;
import com.betos.quanda.repository.QuestionRepository;

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
	public Answer addAnswer(@PathVariable Long questionId,
			@Valid @RequestBody Answer answer) {
		return questionRepository.findById(questionId)
				.map(question -> {
					answer.setQuestion(question);
					return answerRepository.save(answer);
				}).orElseThrow(() -> new ResourceNotFoundException("Question not found with id" + questionId));
	}
}
