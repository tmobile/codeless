package com.tmobile.ct.codeless.service.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

import com.tmobile.ct.codeless.core.Executor;
import com.tmobile.ct.codeless.service.Call;
import com.tmobile.ct.codeless.service.HttpRequest;
import com.tmobile.ct.codeless.service.model.swagger.SwaggerReader;
import com.tmobile.ct.codeless.service.restassured.RestAssuredHttpClient;


public class SwaggerParserTest {

//	@Test
	public void itShouldReadPetStoreSwaggerYaml(){
		List<HttpRequest> requests = new SwaggerReader().parse("/model/petstore/swagger.yaml");
		
		assertThat(requests).describedAs("request list").isNotEmpty();
		
		System.out.println("\n\n"+requests);
		
		requests.stream().forEach(req ->{
			if(req.getOperationPath().getValue().equalsIgnoreCase("/store/inventory")){
				Call call = new Call(new RestAssuredHttpClient(), req, 0);
				new Executor().run(call);
			}
		});
	}
	
	@Test
	public void itShouldReadBankSwaggerYaml(){
		List<HttpRequest> requests = new SwaggerReader().parse("/model/bank/swagger.yaml");
		
		assertThat(requests).describedAs("request list").isNotEmpty();
		
		System.out.println("\n\n"+requests);

		// TODO Replace with mock infrastructure so calls can be made
//		requests.stream().forEach(req ->{
//			
//				Call call = new Call(new RestAssuredHttpClient(), req, 0);
//				new Executor().run(call);
//			
//		});
	}
	
	
}
