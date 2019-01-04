package com.tmobile.ct.codeless.service.assertion;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import io.restassured.path.json.JsonPath;

public class JsonPathTests {

	@Test
	public void jsonPathShouldWorkFromStringJsonField(){
		String json = "{\"a\": \"b\"}";
		JsonPath jsonPath = new JsonPath(json);
		String actual = jsonPath.getString("a");
		System.out.println(actual);
		assertThat(actual).isEqualTo("b");
	}
	
	@Test
	public void jsonPathShouldWorkFromNumberJsonField(){
		String json = "{\"a\": 1}";
		JsonPath jsonPath = new JsonPath(json);
		Long actual = jsonPath.getLong("a");
		Long expected = 1L;
		System.out.println(actual);
		assertThat(actual).isEqualTo(expected);
	}
	
	@Test
	public void jsonPathShouldWorkFromBooleanJsonField(){
		String json = "{\"a\": true}";
		JsonPath jsonPath = new JsonPath(json);
		Boolean actual = jsonPath.getBoolean("a");
		Boolean expected = true;
		System.out.println(actual);
		assertThat(actual).isEqualTo(expected);
	}
}
