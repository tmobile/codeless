package com.tmobile.ct.codeless.service.restassured;

import org.junit.Test;
import org.junit.Assert;

import com.tmobile.ct.codeless.service.HttpRequest;
import com.tmobile.ct.codeless.service.model.postman.PostmanParser;
import com.tmobile.ct.codeless.service.model.swagger.SwaggerReader;

import io.restassured.specification.RequestSpecification;

import java.util.List;

public class RestAssuredRequestBuilderTest {
	@Test
	public void itShouldBuildFromSwagger() {
		List<HttpRequest> requests = new SwaggerReader().parse("/model/bank/swagger.yaml");
		requests.stream().forEach(req ->{
			RequestSpecification spec = RestAssuredRequestBuilder.build(req);
			Assert.assertNotNull(spec);
		});
	}
	
	@Test
	public void itShouldBuildFromCollection() {
		List<HttpRequest> requests = new PostmanParser().parse("/model/bank/postman_collection.json");
		requests.stream().forEach(req ->{
			RequestSpecification spec = RestAssuredRequestBuilder.build(req);
			Assert.assertNotNull(spec);
		});
	}
}
