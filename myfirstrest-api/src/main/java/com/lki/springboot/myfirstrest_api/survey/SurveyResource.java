package com.lki.springboot.myfirstrest_api.survey;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class SurveyResource {

	SurveyService surveyService;

	public SurveyResource(SurveyService surveyService) {
		super();
		this.surveyService = surveyService;
	}

	@RequestMapping("/surveys")
	public List<Survey> listAllSurveys() {
		return surveyService.retrieveSurveys();
	}

	// der Name in {} MUSS der gleiche wie beim Parameter sein
	@RequestMapping("/surveys/{surveyId}")
	public Survey listAllSurveys(@PathVariable String surveyId) {
		Optional<Survey> surveyById = surveyService.retrieveSurveyById(surveyId);
		if (surveyById.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return surveyById.get();
	}

	@RequestMapping("/surveys/{surveyId}/questions")
	public List<Question> retrieveAllSurveyQuestion(@PathVariable String surveyId) {
		List<Question> questionsFromSurvey = surveyService.retrieveAllQuestionsFromSurvey(surveyId);

		if (questionsFromSurvey.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return questionsFromSurvey;
	}

	@RequestMapping("/surveys/{surveyId}/questions/{questionId}")
	public Question retrieveSpecificSurveyQuestion(@PathVariable String surveyId, @PathVariable String questionId) {
		Optional<Question> questionById = surveyService.retrieveSpecificSurveyQuestion(surveyId, questionId);

		if (questionById.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return questionById.get();
	}

}
