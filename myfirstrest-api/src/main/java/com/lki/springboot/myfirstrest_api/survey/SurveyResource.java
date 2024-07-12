package com.lki.springboot.myfirstrest_api.survey;

import java.util.List;

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
		Survey surveyById = surveyService.retrieveSurveyById(surveyId);
		if (surveyById == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return surveyById;
	}

}
