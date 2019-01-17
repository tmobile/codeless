package com.tmobile.ct.codeless.service;

import org.junit.Test;
import org.junit.Assert;

import com.tmobile.ct.codeless.service.Call;
import com.tmobile.ct.codeless.service.HttpRequest;
import com.tmobile.ct.codeless.service.httpclient.HttpMethod;
import com.tmobile.ct.codeless.service.restassured.RestAssuredHttpClient;

public class CallTest extends BaseWiremockTest {
	@Test
	public void itShouldCall() {
		HttpRequest req = buildDefaultRequest();
		req.setHttpMethod(HttpMethod.GET);
		Call call = new Call(new RestAssuredHttpClient(), req, 0);
		call.run();
	}
}
