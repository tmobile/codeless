package com.tmobile.ct.codeless.service.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmobile.ct.codeless.files.ClassPathUtil;
import com.tmobile.ct.codeless.service.HttpRequest;
import com.tmobile.ct.codeless.service.model.postman.PostmanParser;
import com.tmobile.ct.codeless.service.model.postman.collection.PostmanCollection;
import com.tmobile.ct.codeless.service.model.postman.collection.PostmanItem;



public class PostmanParserTest {

	ObjectMapper om = new ObjectMapper();
	
	@Before
	public void objectMapperInit(){
		
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}
	
	@Test
	public void itShouldReadPostmanCollection(){
		List<HttpRequest> requests = new PostmanParser().parse("/model/bank/postman_collection.json");
		
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
	
	@Test
	public void itShouldReadPostmanCollection2(){
		List<HttpRequest> requests = new PostmanParser().parse("/model/etp_gateway/postman_collection.json");
		
		assertThat(requests).describedAs("request list").isNotEmpty();

	}
	
	@Test
	public void itShouldReadPostmanCollection3(){
		List<HttpRequest> requests = new PostmanParser().parse("/model/etp_reporting/postman_collection.json");
		
		assertThat(requests).describedAs("request list").isNotEmpty();

	}
	
	@Test
	public void jacksonShouldReadPostmanItemClass() throws JsonParseException, JsonMappingException, IOException{
		
		
		PostmanItem item = om.readValue(Paths.get(ClassPathUtil.getAbsolutePath("/testitem.json")).toFile(), PostmanItem.class);
		
		assertThat(item.request.method).describedAs("request method").isEqualTo("GET");
		
//		System.out.println(item.request.method);
	}
	
	@Test
	public void jacksonShouldReadPostmanFolderClass() throws JsonParseException, JsonMappingException, IOException{
		PostmanCollection collection = om.readValue(Paths.get(ClassPathUtil.getAbsolutePath("/model/bank/postman_collection.json")).toFile(), PostmanCollection.class);
		
		assertThat(collection.item.get(0).request.method).describedAs("request method").isEqualTo("GET");
		
		System.out.println(om.writerWithDefaultPrettyPrinter().writeValueAsString(collection));
	}
	
}
