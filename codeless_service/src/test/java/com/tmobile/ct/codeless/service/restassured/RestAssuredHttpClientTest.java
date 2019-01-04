package com.tmobile.ct.codeless.service.restassured;

import org.junit.Test;
import org.junit.Assert;

import com.tmobile.ct.codeless.service.BaseWiremockTest;
import com.tmobile.ct.codeless.service.HttpRequest;
import com.tmobile.ct.codeless.service.HttpResponse;
import com.tmobile.ct.codeless.service.httpclient.HttpMethod;


public class RestAssuredHttpClientTest extends BaseWiremockTest {
	@Test
	public void itShouldGet() {
		RestAssuredHttpClient client = new RestAssuredHttpClient();
		HttpRequest req = buildDefaultRequest();
		req.setHttpMethod(HttpMethod.GET);
		HttpResponse response = client.call(req);
		Assert.assertEquals(200, (int) new Integer(response.getStatusCode()));
	}
	
	@Test
	public void itShouldPost() {
		RestAssuredHttpClient client = new RestAssuredHttpClient();
		HttpRequest req = buildDefaultRequest();
		req.setHttpMethod(HttpMethod.POST);
		HttpResponse response = client.call(req);
		Assert.assertEquals(200, (int) new Integer(response.getStatusCode()));
	}
	
	@Test
	public void itShouldPut() {
		RestAssuredHttpClient client = new RestAssuredHttpClient();
		HttpRequest req = buildDefaultRequest();
		req.setHttpMethod(HttpMethod.PUT);
		HttpResponse response = client.call(req);
		Assert.assertEquals(200, (int) new Integer(response.getStatusCode()));
	}
	
	@Test
	public void itShouldPatch() {
		RestAssuredHttpClient client = new RestAssuredHttpClient();
		HttpRequest req = buildDefaultRequest();
		req.setHttpMethod(HttpMethod.PATCH);
		HttpResponse response = client.call(req);
		Assert.assertEquals(200, (int) new Integer(response.getStatusCode()));
	}
	
	@Test
	public void itShouldDelete() {
		RestAssuredHttpClient client = new RestAssuredHttpClient();
		HttpRequest req = buildDefaultRequest();
		req.setHttpMethod(HttpMethod.DELETE);
		HttpResponse response = client.call(req);
		Assert.assertEquals(200, (int) new Integer(response.getStatusCode()));
	}
	
	@Test
	public void itShouldOptions() {
		RestAssuredHttpClient client = new RestAssuredHttpClient();
		HttpRequest req = buildDefaultRequest();
		req.setHttpMethod(HttpMethod.OPTIONS);
		HttpResponse response = client.call(req);
		Assert.assertEquals(200, (int) new Integer(response.getStatusCode()));
	}
	
	@Test
	public void itShouldHead() {
		RestAssuredHttpClient client = new RestAssuredHttpClient();
		HttpRequest req = buildDefaultRequest();
		req.setHttpMethod(HttpMethod.HEAD);
		HttpResponse response = client.call(req);
		Assert.assertEquals(200, (int) new Integer(response.getStatusCode()));
	}
}
