package com.tmobile.ct.codeless.service.core;

import java.util.concurrent.Future;

import com.tmobile.ct.codeless.core.Step;
import com.tmobile.ct.codeless.service.HttpClient;
import com.tmobile.ct.codeless.service.HttpRequest;
import com.tmobile.ct.codeless.service.HttpResponse;
import com.tmobile.ct.codeless.service.model.Operation;

/**
 * The Interface ServiceCall.
 *
 * @author Rob Graff
 */
public interface ServiceCall extends Step {

	/**
	 * Gets the http request.
	 *
	 * @return the http request
	 */
	HttpRequest getHttpRequest();
	
	/**
	 * Sets the http request.
	 *
	 * @param request the new http request
	 */
	void setHttpRequest(HttpRequest request);
	
	/**
	 * Gets the http response.
	 *
	 * @return the http response
	 */
	HttpResponse getHttpResponse();
	
	/**
	 * Sets the http response.
	 *
	 * @param response the new http response
	 */
	void setHttpResponse(HttpResponse response);
	
	/**
	 * Gets the http client.
	 *
	 * @return the http client
	 */
	HttpClient getHttpClient();
	
	/**
	 * Sets the http client.
	 *
	 * @param client the new http client
	 */
	void setHttpClient(HttpClient client);
	
	/**
	 * Gets the operation.
	 *
	 * @return the operation
	 */
	Operation getOperation();
	
	/**
	 * Sets the operation.
	 *
	 * @param operation the new operation
	 */
	void setOperation(Operation operation);
	
	/**
	 * Checks if is complete.
	 *
	 * @return the future
	 */
	Future<Boolean> isComplete();
	
	/**
	 * Mark complete.
	 */
	void markComplete();
}
