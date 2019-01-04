package com.tmobile.ct.codeless.service;

import com.tmobile.ct.codeless.service.httpclient.Headers;

/**
 * The Interface HttpClient.
 *
 * @author Rob Graff
 */
public interface HttpClient {

	/**
	 * Call.
	 *
	 * @param operation the operation
	 * @return the http response
	 */
	HttpResponse call(HttpRequest operation);
	
	/**
	 * Call.
	 *
	 * @return the http response
	 */
	HttpResponse call();

	/**
	 * Builds the.
	 *
	 * @param operation the operation
	 */
	void build(HttpRequest operation);

	/**
	 * Invoke request modifiers.
	 */
	void invokeRequestModifiers();
	
	
}
