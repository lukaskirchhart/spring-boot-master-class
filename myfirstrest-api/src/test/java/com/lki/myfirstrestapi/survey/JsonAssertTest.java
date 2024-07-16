package com.lki.myfirstrestapi.survey;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

public class JsonAssertTest {

	@Test
	public void testJsonAssertTest() throws JSONException {
		String response = "{\"id\":"
				+ "\"Question1\",\"description\":\"Most Popular Cloud Platform Today\",\"options\":[\"AWS\",\"Azure\",\"Google Cloud\",\"Oracle Cloud\"],\"correctAnswer\":\"AWS\"}";
		String expectedBody = "{\"id\":\"Question1\",\"description\":\"Most Popular Cloud Platform Today\",\"options\":[\"AWS\",\"Azure\",\"Google Cloud\",\"Oracle Cloud\"],\"correctAnswer\":\"AWS\"}";

		JSONAssert.assertEquals(expectedBody, response, true);
	}

	@Test
	public void testNonStrikt() throws JSONException {
		String expectedBody = "{\"id\":\"Question1\",\"description\":\"Most Popular Cloud Platform Today\",\"options\":[\"AWS\",\"Azure\",\"Google Cloud\",\"Oracle Cloud\"],\"correctAnswer\":\"AWS\"}";
		String response = """
				{
				    "id": "Question1",
				    "description": "Most Popular Cloud Platform Today",
				    "options": [
				        "AWS",
				        "Azure",
				        "Google Cloud",
				        "Oracle Cloud"
				    ],
				    "correctAnswer": "AWS"
				}
				""";

		JSONAssert.assertEquals(expectedBody, response, false);
	}

}
