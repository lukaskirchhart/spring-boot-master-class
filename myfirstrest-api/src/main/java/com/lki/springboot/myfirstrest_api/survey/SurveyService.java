package com.lki.springboot.myfirstrest_api.survey;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class SurveyService {

	private static List<Survey> surveys = new ArrayList<>();

	static {
		Question question1 = new Question("Question1", "Most Popular Cloud Platform Today",
				Arrays.asList("AWS", "Azure", "Google Cloud", "Oracle Cloud"), "AWS");
		Question question2 = new Question("Question2", "Fastest Growing Cloud Platform",
				Arrays.asList("AWS", "Azure", "Google Cloud", "Oracle Cloud"), "Google Cloud");
		Question question3 = new Question("Question3", "Most Popular DevOps Tool",
				Arrays.asList("Kubernetes", "Docker", "Terraform", "Azure DevOps"), "Kubernetes");

		List<Question> questions = new ArrayList<>(Arrays.asList(question1, question2, question3));

		Survey survey = new Survey("Survey1", "My Favorite Survey", "Description of the Survey", questions);

		surveys.add(survey);
	}

	public List<Survey> retrieveSurveys() {
		return surveys;
	}

	public Optional<Survey> retrieveSurveyById(String name) {
		Optional<Survey> optional = surveys.stream().filter(s -> s.getId().equals(name)).findAny();
		return optional;
	}

	public List<Question> retrieveAllQuestionsFromSurvey(String name) {
		Optional<Survey> surveyById = retrieveSurveyById(name);
		return surveyById.isPresent() ? surveyById.get().getQuestions() : Collections.emptyList();
	}

	public Optional<Question> retrieveSpecificSurveyQuestion(String surveyId, String questionId) {
		Optional<Question> questionById = Optional.empty();
		Optional<Survey> surveyById = retrieveSurveyById(surveyId);
		if (surveyById.isPresent()) {
			List<Question> questions = surveyById.get().getQuestions();
			questionById = questions.stream().filter(q -> q.getId().equalsIgnoreCase(questionId)).findFirst();
		}
		return questionById;
	}
}
