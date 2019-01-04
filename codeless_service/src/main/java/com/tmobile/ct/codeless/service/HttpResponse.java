package com.tmobile.ct.codeless.service;

/**
 * The Interface HttpResponse.
 *
 * @author Rob Graff
 * @param <T> the generic type
 */
public interface HttpResponse<T> extends HttpRequest<T>{

	/**
	 * Gets the status code.
	 *
	 * @return the status code
	 */
	Integer getStatusCode();
	
	/**
	 * Sets the status code.
	 *
	 * @param statusCode the new status code
	 */
	void setStatusCode(Integer statusCode);
	
	/**
	 * Gets the response time.
	 *
	 * @return the response time
	 */
	Long getResponseTime();
	
	/**
	 * Sets the response time.
	 *
	 * @param responseTime the new response time
	 */
	void setResponseTime(Long responseTime);
}
