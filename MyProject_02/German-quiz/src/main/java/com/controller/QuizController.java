package com.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model.Quiz;
import com.model.QuizQuestionCounter;
import com.service.QuizService;

@RestController
@CrossOrigin("http://localhost:4200")
public class QuizController {

	private QuizService quizService;

	public QuizController(QuizService quizService) {
		this.quizService = quizService;
	}
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Quiz createNewQuizQuestion() {
		return quizService.createNewQuizQuestion(new Quiz());
	}
	
	@RequestMapping(value = "check",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public QuizQuestionCounter checkResponse(@RequestParam String german, @RequestParam String hungarian) {
		return quizService.updateCounter(german, hungarian);
	}
	
}
