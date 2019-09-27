package com.betos.quanda.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.betos.quanda.exception.ResourceNotFoundException;
import com.betos.quanda.model.Answer;
import com.betos.quanda.repository.AnswerRepository;
import com.betos.quanda.repository.QuestionRepository;

@Service
public class AnswerService {

	@Autowired
	private AnswerRepository answerRepository;
	
	@Autowired
	private QuestionRepository questionRepository;
	
	public List<Answer> getAnswerbyQuestionId(Long questionId) {
		return answerRepository.findByQuestionId(questionId);
	}
	
	public Answer create(Long questionId, Answer answer) {
		return questionRepository.findById(questionId)
				.map(question -> {
					answer.setQuestion(question);
					return answerRepository.save(answer);
				}).orElseThrow(() -> new ResourceNotFoundException("Question not found with id" + questionId));
	}
	
	public Answer put(Long questionId, Long answerId, Answer answerRequest) {
		if(!questionRepository.existsById(questionId)) {
			throw new ResourceNotFoundException("Question not found with id" + questionId);
		}
		
		return answerRepository.findById(answerId)
				.map(answer -> {
					answer.setText(answerRequest.getText());
					return answerRepository.save(answer);
				}).orElseThrow(() -> new ResourceNotFoundException("Answer not found with id" + answerId));
	}
	
	public ResponseEntity<?> delete(Long answerId, Long questionId) {
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
