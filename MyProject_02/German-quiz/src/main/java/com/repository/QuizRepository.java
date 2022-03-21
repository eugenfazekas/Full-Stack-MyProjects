package com.repository;

import org.springframework.stereotype.Repository;

@Repository
public class QuizRepository {
	
	String [] germanDb; 
	String [] hungarianDb;
	
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
