/*******************************************************************************
 * * Copyright 2018 T Mobile, Inc. or its affiliates. All Rights Reserved.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 *  * use this file except in compliance with the License.  You may obtain a copy
 *  * of the License at
 *  *
 *  *   http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 *  * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 *  * License for the specific language governing permissions and limitations under
 *  * the License.
 ******************************************************************************/
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
