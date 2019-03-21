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
package com.tmobile.ct.codeless.service.restassured;

import java.util.List;
import java.util.Optional;

import com.tmobile.ct.codeless.service.HttpClient;
import com.tmobile.ct.codeless.service.HttpRequest;
import com.tmobile.ct.codeless.service.HttpResponse;
import com.tmobile.ct.codeless.testdata.RequestModifier;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * The Class RestAssuredHttpClient.
 *
 * @author Rob Graff
 */
public class RestAssuredHttpClient implements HttpClient{

	/** The req spec builder. */
	private final RequestSpecBuilder reqSpecBuilder;

	/** The req spec. */
	private RequestSpecification reqSpec;

	/** The request. */
	private HttpRequest request;

	/** The response. */
	private HttpResponse response;

	/**
	 * Instantiates a new rest assured http client.
	 */
	public RestAssuredHttpClient(){
		this.reqSpecBuilder = new RequestSpecBuilder();
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.HttpClient#build(com.tmobile.ct.codeless.service.HttpRequest)
	 */
	@Override
	public void build(HttpRequest request) {
		this.request = request;
		invokeRequestModifiers();
		reqSpec = RestAssuredRequestBuilder.build(request);

	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.HttpClient#call()
	 */
	@Override
	public HttpResponse call(){
		response = sendRequest();
		System.err.println("Res Body: "+response.getBody().asString());
		return response;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.HttpClient#call(com.tmobile.ct.codeless.service.HttpRequest)
	 */
	@Override
	public HttpResponse call(HttpRequest request){
		build(request);
		return call();
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.HttpClient#invokeRequestModifiers()
	 */
	@Override
	public void invokeRequestModifiers() {
		if(request == null) return;
		List<RequestModifier> modifiers = request.getRequestModifiers();
		modifiers.forEach(modifier -> modifier.modify(request));

	}

	/**
	 * Send request.
	 *
	 * @return the http response
	 */
	private HttpResponse sendRequest(){
		return Optional.ofNullable(chooseMethod()).map(RestAssuredResponseParser::parse).orElse(null);
	}

	/**
	 * Choose method.
	 *
	 * @return the response
	 */
	private Response chooseMethod(){

		RestAssured rest = new RestAssured();
		rest.useRelaxedHTTPSValidation();
		Response response = null;
		String target = "";
		if(request.getOperationPath() != null) {
			target = RestAssuredRequestBuilder.mapOperationPath(request.getOperationPath());
		}
		switch (request.getHttpMethod()){
		case POST:
			response = rest.given(reqSpec).post(target);
			break;
		case PUT:
			response = RestAssured.given(reqSpec).put(target);//reqSpec.put(target);
			break;
		case PATCH:
			response = RestAssured.given(reqSpec).patch(target);//reqSpec.patch(target);
			break;
		case GET:
			response = RestAssured.given(reqSpec).get(target);//reqSpec.get(target);
			break;
		case DELETE:
			response = RestAssured.given(reqSpec).delete(target);//reqSpec.delete(target);
			break;
		case OPTIONS:
			response = RestAssured.given(reqSpec).options(target);//reqSpec.options(target);
			break;
		case HEAD:
			response = RestAssured.given(reqSpec).head(target);//reqSpec.head(target);
			break;
		}

		return response;
	}
}
