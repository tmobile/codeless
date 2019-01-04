package com.tmobile.ct.codeless.service.assertion;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.tmobile.ct.codeless.assertion.AssertJAssertionBuilder;
import com.tmobile.ct.codeless.service.HttpResponse;
import com.tmobile.ct.codeless.service.accessor.response.JsonPathAccessor;
import com.tmobile.ct.codeless.service.core.ServiceCall;
import com.tmobile.ct.codeless.service.httpclient.Body;

public class BaseServiceCallAssertionTest {

	String jsonPathKey = "id";
	Long expected = 1L;
	String method = "isEqualTo";
	ServiceCall call;
	HttpResponse response;
	Body body;
	
	String jsonbody = "{ \"id\": 1 }";
	
	@Before
	public void setup(){
		call = mock(ServiceCall.class);
		response = mock(HttpResponse.class);
		body = mock(Body.class);
		when(body.asString()).thenReturn(jsonbody);
		when(response.getBody()).thenReturn(body);
		when(call.getHttpResponse()).thenReturn(response);
	}
	
	@Test
	public void itShouldInstanciate(){
		BaseServiceCallAssertion assertion = new BaseServiceCallAssertion<Long>(
				new JsonPathAccessor<Long>(jsonPathKey, Long.class), Long.valueOf(expected), method, 
				new AssertJAssertionBuilder(), Long.class, call);
		
		assertion.run();
	}
}
