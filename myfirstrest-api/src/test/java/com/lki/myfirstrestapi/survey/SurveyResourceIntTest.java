package com.lki.myfirstrestapi.survey;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

// http://localhost:8080/surveys/Survey1/questions/Question1
// http://localhost:RANDOM_PORT/surveys/Survey1/questions/Question1
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SurveyResourceIntTest {

	@Autowired
	TestRestTemplate template;

//	public SurveyResourceIntTest(TestRestTemplate testRestTemplate) {
//		super();
//		this.testRestTemplate = testRestTemplate;
//	}

	private static String URL = "/surveys/Survey1/questions/Question1";

	private static String ALL_QUESTIONS_URL = "/surveys/Survey1/questions";

	// [Content-Type:"application/json", Transfer-Encoding:"chunked", Date:"Mon, 15
	// Jul 2024 14:11:19 GMT", Keep-Alive:"timeout=60", Connection:"keep-alive"]

	@Test
	public void retrieveSpecificSurveyTest() throws JSONException {
		String response = """
				{
				    "id": "Question1",
				    "description": "Most Popular Cloud Platform Today Change",
				    "options": [
				        "AWS",
				        "Azure",
				        "Google Cloud",
				        "Oracle Cloud"
				    ],
				    "correctAnswer": "Google Cloud"
				}
				""";
		ResponseEntity<String> responseEntity = template.getForEntity(URL, String.class);

		assertTrue(responseEntity.getStatusCode().is2xxSuccessful(), "success code");
		String firstHeader = responseEntity.getHeaders().get("Content-Type").get(0);
		assertEquals("application/json", firstHeader, "header");

		String expectedBody = "{\"id\":\"Question1\",\"description\":\"Most Popular Cloud Platform Today\",\"options\":[\"AWS\",\"Azure\",\"Google Cloud\",\"Oracle Cloud\"],\"correctAnswer\":\"AWS\"}";
		JSONAssert.assertEquals(expectedBody, responseEntity.getBody(), false);
	}

	@Test
	public void retrieveAllQuestions() throws JSONException {
		// check that there are 3 questions with specific id but their content does not
		// matter
		String expectedBody = """
				[
				  {
				    "id": "Question1"
				  },
				  {
				    "id": "Question2"
				  },
				  {
				    "id": "Question3"
				  }
				]
								""";
		ResponseEntity<String> responseEntity = template.getForEntity(ALL_QUESTIONS_URL, String.class);

		assertTrue(responseEntity.getStatusCode().is2xxSuccessful(), "success code");
		String firstHeader = responseEntity.getHeaders().get("Content-Type").get(0);
		assertEquals("application/json", firstHeader, "header");

		JSONAssert.assertEquals(expectedBody, responseEntity.getBody(), false);
	}

	/**
	 * Problem: side effect on other tests -> create Question in DB
	 */
	@Test
	void tesAddNewSurveyQuestionPost() throws Exception {
		String requestBody = """
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

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		HttpEntity<String> httpEntity = new HttpEntity<String>(requestBody, headers);
		ResponseEntity<String> responseEntity = template.exchange(ALL_QUESTIONS_URL, HttpMethod.POST, httpEntity,
				String.class);

		assertTrue(responseEntity.getStatusCode().is2xxSuccessful(), "success code");
		String location = responseEntity.getHeaders().get("Location").get(0);
		assertTrue(location.contains(ALL_QUESTIONS_URL), "header");

		template.delete(location);

		// TODO lki kann man auch mit einer Annotation sagen, dass gemockt werden soll?
	}

	@Test
	void tesAddNewSurveyPost() throws Exception {
		String requestBody = """
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

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(ALL_QUESTIONS_URL)
				.accept(org.springframework.http.MediaType.APPLICATION_JSON);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		HttpEntity<String> httpEntity = new HttpEntity<String>(requestBody, headers);
		ResponseEntity<String> responseEntity = template.exchange(ALL_QUESTIONS_URL, HttpMethod.POST, httpEntity,
				String.class);

		assertTrue(responseEntity.getStatusCode().is2xxSuccessful(), "success code");
		String location = responseEntity.getHeaders().get("Location").get(0);
		assertTrue(location.contains(ALL_QUESTIONS_URL), "header");

		template.delete(location);

		// TODO lki kann man auch mit einer Annotation sagen, dass gemockt werden soll?
	}

}
