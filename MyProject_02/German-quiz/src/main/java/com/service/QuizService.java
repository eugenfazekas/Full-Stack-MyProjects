package com.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.model.Quiz;
import com.model.QuizQuestionCounter;
import com.repository.QuizRepository;

@Service
public class QuizService {
	
	private QuizRepository quizRepository;
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	public QuizService(QuizRepository quizRepository) {
		this.quizRepository = quizRepository;
	}

	public int getRandomNumber(int min, int max) {
	    return (int) ((Math.random() * (max - min)) + min);
	}
	
	public QuizQuestionCounter updateCounter(String german, String hungarian) {
		
		String checked = checkResponse(german,hungarian);
		int quizQuestionCounter = quizRepository.getQuizRepoQuestionCounter();
		int quizCounter = quizRepository.getQuizRepoCounter();
		int quizGoodQuestionCounter = quizRepository.getQuizGoodQuestionCounter();
		
		if(checked == null && quizQuestionCounter < 26) {
			quizGoodQuestionCounter++;
			quizRepository.setQuizGoodQuestionCounter(quizGoodQuestionCounter);
		}
		
		else if(checked == null && quizQuestionCounter == 26) {
			quizQuestionCounter++;
			quizRepository.setQuizRepoCounter(quizQuestionCounter);
			quizRepository.setQuizGoodQuestionCounter(0);		
		}
		
		if(!hungarian.equals("null")) {
			quizRepository.setQuizRepoQuestionCounter(quizQuestionCounter+1);
			quizQuestionCounter++;
		}
		QuizQuestionCounter counter = new QuizQuestionCounter();
		counter.setQuizQuestionCounter(quizQuestionCounter);
		counter.setQuizGoodQuestionCounter(quizGoodQuestionCounter);
		counter.setQuizCounter(quizCounter);
		counter.setGoodResponse(checked);
		return counter;
	}
	
	public Quiz createNewQuizQuestion(Quiz quiz) {
		
		Quiz newQuiz = new Quiz();
		String responses[] = new String[4];
		
		int[] randomIndexNumbers = getRandomIndexes();
		int randomQuestionIndex = randomIndexNumbers[getRandomNumber(0,4)];
		
		for (int i = 0; i < responses.length; i++) {
			responses[i] = quizRepository.getHungarianWordFromdDb(randomIndexNumbers[i]);
		}
		newQuiz.setQuizWord(quizRepository.getGermanWordFromDb(randomQuestionIndex));
		newQuiz.setQuizResponses(responses);
		log.debug(newQuiz.toString());
		return newQuiz;
	}
	
	int[] getRandomIndexes() {
		
		int random[] = new int[4];
		int counter = 0;
		int index;
		boolean checked = false;

		while(counter < 4) {
			checked = true;
			index = getRandomNumber(0,quizRepository.getHungarianDb().length);
			String indexValue = quizRepository.getHungarianDb()[index];
			if(indexValue.length() ==  1 )
					checked = false;		
			for (int  i = 0; i < random.length; i++) {
				if(index == random[i]) {
					checked = false;
				}
			}
						
			if(checked == true) {
				random[counter] = index;
				counter++;
			}		
		}
		return random;
	}
	
	public String checkResponse(String german, String hungarian) {
		
		int germanIndex = -1;
		int hungarianIndex = 0;
		String germanDb[] = quizRepository.getGermanDb();
		String hungarianDb[] = quizRepository.getHungarianDb();
			
		for(int i = 0; i < germanDb.length; i++) 
			if(germanDb[i].equals(german))
				germanIndex = i;
		
		
		for(int i = 0; i < hungarianDb.length; i++) 
			if(hungarianDb[i].equals(hungarian))
				hungarianIndex = i;
		
		String goodResponse = hungarianDb[germanIndex];

		return germanIndex == hungarianIndex ? null : goodResponse;
	}
}
