package com.model;

public class QuizQuestionCounter {

	private int quizQuestionCounter;
	private int quizGoodQuestionCounter;
	private int quizCounter;
	private String goodResponse;
	
	public int getQuizQuestionCounter() {
		return quizQuestionCounter;
	}
	
	public void setQuizQuestionCounter(int quizQuestionCounter) {
		this.quizQuestionCounter = quizQuestionCounter;
	}
	
	public int getQuizGoodQuestionCounter() {
		return quizGoodQuestionCounter;
	}
	
	public void setQuizGoodQuestionCounter(int quizGoodQuestionCounter) {
		this.quizGoodQuestionCounter = quizGoodQuestionCounter;
	}
	
	public int getQuizCounter() {
		return quizCounter;
	}
	
	public void setQuizCounter(int quizCounter) {
		this.quizCounter = quizCounter;
	}
	
	public String getGoodResponse() {
		return goodResponse;
	}
	public void setGoodResponse(String goodResponse) {
		this.goodResponse = goodResponse;
	}  
}
