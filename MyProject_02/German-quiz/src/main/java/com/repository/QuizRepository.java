package com.repository;

import org.springframework.stereotype.Repository;

@Repository
public class QuizRepository {
	
	private int quizRepoQuestionCounter;
	private int quizRepoCounter;
	private int quizGoodQuestionCounter;
	
	String [] germanDb; 
	String [] hungarianDb;
		
	public QuizRepository() {
		this.quizRepoQuestionCounter = 0;
		this.quizRepoCounter = 0;
	}
	
	public int getQuizGoodQuestionCounter() {
		return quizGoodQuestionCounter;
	}

	public void setQuizGoodQuestionCounter(int quizGoodQuestionCounter) {
		this.quizGoodQuestionCounter = quizGoodQuestionCounter;
	}

	public int getQuizRepoQuestionCounter() {
		return quizRepoQuestionCounter;
	}

	public void setQuizRepoQuestionCounter(int quizRepoQuestionCounter) {
		this.quizRepoQuestionCounter = quizRepoQuestionCounter;
	}

	public int getQuizRepoCounter() {
		return quizRepoCounter;
	}

	public void setQuizRepoCounter(int quizRepoCounter) {
		this.quizRepoCounter = quizRepoCounter;
	}

	public String getGermanWordFromDb(int index) {
		return this.germanDb[index];
	}
	
	public void addToGermanDb(String word, int index) {		
		this.germanDb[index] = word;
	}
	
	public String getHungarianWordFromdDb(int index) {
		return hungarianDb[index];
	}
	
	public void addToHungarianDb(String word, int index) {
		this.hungarianDb[index] = word;
	}

	public String[] getGermanDb() {
		return germanDb;
	}

	public void setGermanDb(String[] germanDb) {
		this.germanDb = germanDb;
	}

	public String[] getHungarianDb() {
		return hungarianDb;
	}

	public void setHungarianDb(String[] hungarianDb) {
		this.hungarianDb = hungarianDb;
	}	
}
