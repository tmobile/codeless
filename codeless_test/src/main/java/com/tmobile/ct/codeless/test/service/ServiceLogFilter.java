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
package com.tmobile.ct.codeless.test.service;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmobile.ct.codeless.service.HttpRequest;
import com.tmobile.ct.codeless.service.HttpResponse;
import com.tmobile.ct.codeless.service.core.ServiceCall;
import com.tmobile.ct.codeless.service.httpclient.QueryParams;

import io.restassured.http.Method;

/**
 * The Class ServiceLogFilter.
 *
 * @author Sai Chandra Korpu
 */
public class ServiceLogFilter {

	public static final Logger logger = LoggerFactory.getLogger(ServiceLogFilter.class);

	public static ServiceCallDTO filter(ServiceCall serviceCall) {
		logger.debug("Entering ServiceLogFilter.filter() for [{}]", serviceCall);
		ObjectMapper mapper = new ObjectMapper();
		ServiceCallDTO call = new ServiceCallDTO();
		HttpRequest request = serviceCall.getHttpRequest();

		call.setHttpMethod(Method.valueOf(request.getHttpMethod().toString()));
		call.setRequestQueryParams(mapQueryParams(request.getQueryParams()));
		call.setRequestHeaders(mapHeaders(request.getHeaders()));

		try {
			Object body = request.getBody();
			if (body != null) {
				if (body instanceof String && ((String) body).indexOf("\n") > 0) {
					call.setRequestBody((String) body);
				} else {
					String requestBody = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(body).replace("\\",
							"");
					call.setRequestBody(requestBody);
				}
			}
		} catch (JsonProcessingException e) {
			logger.warn("error parsing request body to pretty printed string, ex: {}", e);
		}

		call.setSentAt(ZonedDateTime.now());

		HttpResponse response = serviceCall.getHttpResponse();
		if (response == null) {
			logger.error("Response is null, returning call data");
			return call;

		} else if (!response.isSuccess()) {
			logger.error("Error response: [{}]", response.getStatusCode());
		}

		call.setStatusCode(response.getStatusCode());
		call.setResponseTime(response.getResponseTime());
		call.setResponseHeaders(mapHeaders(response.getHeaders()));

		try {
			Object body = response.getBody();
			if (body != null) {
				if (body instanceof String && ((String) body).indexOf("\n") > 0) {
					call.setResponseBody((String) body);
				} else {
					String responseBody = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(body).replace("\\",
							"");
					call.setResponseBody(responseBody);
				}
			}
		} catch (JsonProcessingException e) {
			logger.warn("error parsing request body to pretty printed string, ex: {}", e);
		}

		return call;
	}

	private static Map<String, String> mapHeaders(com.tmobile.ct.codeless.service.httpclient.Headers headers) {

		if (headers == null || headers.size() < 1) {
			return null;
		}

		Map<String, String> output = new LinkedHashMap<>();

		for (String key : headers.keySet()) {
			output.put(key, String.valueOf(headers.get(key)));
		}
		return output;
	}

	private static Map<String, String> mapQueryParams(QueryParams input) {

		if (input == null || input.size() < 1) {
			return null;
		}

		HashMap<String, String> out = new HashMap<>();
		for (String key : input.keySet()) {
			out.put(key, String.valueOf(input.get(key)));
		}
		return out;
	}
}
