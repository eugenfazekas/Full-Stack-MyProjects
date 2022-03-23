package com.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.repository.QuizRepository;

@Service
public class DataBindService {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private InputStringService inputStringService;
	private QuizRepository quizRepository;
	
	public DataBindService(InputStringService inputStringService, QuizRepository quizRepository) {
		super();
		this.inputStringService = inputStringService;
		this.quizRepository = quizRepository;
	}

	String[] loadLibrary() {
		
		int index = 0;
		int libraryLength = countLibraryLength();
		
		quizRepository.setGermanDb(new String[libraryLength]);
		quizRepository.setHungarianDb(new String[libraryLength]);
		
		for (int i = 0 ; i < libraryPath().length ; i++)	{
		String rawInputFileString = inputStringService.rawString(libraryPath()[i]);
		String[] ge_hu_pair_array = splitElements(rawInputFileString,";");

			for(int j = 0; j < ge_hu_pair_array.length; j++ ) {
				String ge_hu_pair[] = splitElements(ge_hu_pair_array[j],"=");
				//log.debug("ge_hu_pair[0]: "+ge_hu_pair[0]+ " ge_hu_pair[1]: "+ge_hu_pair[1]);
				quizRepository.addToGermanDb(ge_hu_pair[0], index);
				quizRepository.addToHungarianDb(ge_hu_pair[1], index);
				index++;
			}
		}
		log.debug(""+index+". words inserted to library");
		return null;
	}

	String[] splitElements(String input, String regex) {
		String split [] = input.split(regex);
		//log.debug("BindEmployeeService splitElements split 0: "+ split[0].toString());
			return split;
	}
	
	String[] libraryPath() {
		String path[] = {"static/egyeb_szavak.txt","static/fonevek.txt","static/igek.txt","static/melleknevek.txt"};
		return path;
	}
	
	int countLibraryLength() {
		
		int index = 0;
		for (int i = 0 ; i < libraryPath().length ; i++)	{
			String rawInputFileString = inputStringService.rawString(libraryPath()[i]);
			String[] ge_hu_pair_array = splitElements(rawInputFileString,";");
			for(int j = 0; j < ge_hu_pair_array.length; j++ )
				index++;
		}
		return index;
	}
}
