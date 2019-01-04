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
