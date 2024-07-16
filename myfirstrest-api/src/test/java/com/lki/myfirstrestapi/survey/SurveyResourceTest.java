package com.lki.myfirstrestapi.survey;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * This tests test only one Controller + a mocked Service
 */
@WebMvcTest(controllers = SurveyResource.class)
@AutoConfigureMockMvc(addFilters = false) // disable authentification
public class SurveyResourceTest {

	@MockBean
	private SurveyService surveyService;

	@Autowired
	private MockMvc mockMvc;

	private static String URL = "/surveys/Survey1/questions/Question1";

	// mock -> surveyService.addNewSurveyQuestion(surveyId, question);
	// launch only Spring with
	// GET on URL
	@Test
	void testRetrieveSpecificSurveyQuestion_404() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URL).accept(MediaType.APPLICATION_JSON);
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(404, status);
	}

	@Test
	void testRetrieveSpecificSurveyQuestion_200() throws Exception {
		Question question1 = new Question("Question1", "Most Popular Cloud Platform Today",
				Arrays.asList("AWS", "Azure", "Google Cloud", "Oracle Cloud"), "AWS");
		when(surveyService.retrieveSpecificSurveyQuestion("Survey1", "Question1")).thenReturn(Optional.of(question1));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URL).accept(MediaType.APPLICATION_JSON);
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		int status = response.getStatus();
		assertEquals(200, status);

		String expectedRespons = """
				{"id":"Question1","description":"Most Popular Cloud Platform Today","options":["AWS","Azure","Google Cloud","Oracle Cloud"],"correctAnswer":"AWS"}
				""";
		JSONAssert.assertEquals(expectedRespons, response.getContentAsString(), false);
	}

	@Test
	void testAddNewSurveyQuestion() throws Exception {
		String expectedId = "SOME_ID";
		when(surveyService.addNewSurveyQuestion(anyString(), ArgumentMatchers.any()))
				.thenReturn(Optional.of(expectedId));

		String newQuestionJSON = """
					{
					  "description": "Most Popular language Today",
					  "options": [
					    "Java",
					    "C++",
					    "Fortran"
					  ],
					  "correctAnswer": "Java"
					}
				""";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/surveys/Survey1/questions")
				.accept(MediaType.APPLICATION_JSON).content(newQuestionJSON).contentType(MediaType.APPLICATION_JSON);
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		int status = response.getStatus();
		assertEquals(201, status);

		assertTrue(response.getHeader("Location").contains("/surveys/Survey1/questions/" + expectedId));
	}
}
