package com.model;

import java.util.Arrays;

public class Quiz {
	
	private String quizWord;
	private String[] quizResponses;

	
	public String getQuizWord() {
		return quizWord;
	}
	
	public void setQuizWord(String quizWord) {
		this.quizWord = quizWord;
	}
	
	public String[] getQuizResponses() {
		return quizResponses;
	}
	
	public void setQuizResponses(String[] quizResponses) {
		this.quizResponses = quizResponses;
	}

	@Override
	public String toString() {
		return "Quiz [quizWord=" + quizWord + ", quizResponses=" + Arrays.toString(quizResponses) + "]";
	}
}
