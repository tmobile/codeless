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
import java.util.Map;

import io.restassured.http.Method;

/**
 * The Class ServiceCallDTO.
 *
 * @author Sai Chandra Korpu
 */
public class ServiceCallDTO {

	private ZonedDateTime sentAt;

	private Method httpMethod;

	private Map<String, String> requestQueryParams;
	private Map<String, String> requestHeaders;
	private String requestBody;

	private Integer statusCode;
	private String statusString;
	private Long responseTime;

	private Map<String, String> responseHeaders;
	private String responseBody;

	public ZonedDateTime getSentAt() {
		return sentAt;
	}

	public void setSentAt(ZonedDateTime sentAt) {
		this.sentAt = sentAt;
	}

	public Method getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(Method httpMethod) {
		this.httpMethod = httpMethod;
	}

	public Map<String, String> getRequestQueryParams() {
		return requestQueryParams;
	}

	public void setRequestQueryParams(Map<String, String> requestQueryParams) {
		this.requestQueryParams = requestQueryParams;
	}

	public Map<String, String> getRequestHeaders() {
		return requestHeaders;
	}

	public void setRequestHeaders(Map<String, String> requestHeaders) {
		this.requestHeaders = requestHeaders;
	}

	public String getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(String requestBody) {
		this.requestBody = requestBody;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusString() {
		return statusString;
	}

	public void setStatusString(String statusString) {
		this.statusString = statusString;
	}

	public Long getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(Long responseTime) {
		this.responseTime = responseTime;
	}

	public Map<String, String> getResponseHeaders() {
		return responseHeaders;
	}

	public void setResponseHeaders(Map<String, String> responseHeaders) {
		this.responseHeaders = responseHeaders;
	}

	public String getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(String responseBody) {
		this.responseBody = responseBody;
	}

}
