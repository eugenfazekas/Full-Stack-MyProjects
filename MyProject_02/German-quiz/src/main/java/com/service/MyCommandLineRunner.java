package com.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import com.model.Quiz;
import com.repository.QuizRepository;

@Service
public class MyCommandLineRunner implements CommandLineRunner {

	private DataBindService dataBindService;

	private QuizService quizService;
		
	public MyCommandLineRunner(DataBindService dataBindService, QuizRepository quizRepository,
			QuizService quizService) {
		this.dataBindService = dataBindService;
		this.quizService = quizService;
	}

	@Override
	public void run(String... args) throws Exception {
		dataBindService.loadLibrary();
		generatetestquiz();
	}
	
	void generatetestquiz() {
	for(int i = 0; i < 100; i++) {
		System.out.println(quizService.createNewQuizQuestion(new Quiz()).toString());
		}
	}
}
