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

	/**
	 * Gets the step error information.
	 *
	 * @return the error information
	 */
	String getErrorMessage();

	/**
	 * Sets the step error informatio.
	 *
	 * @param errorMessage
	 */
	void setErrorMessage(String errorMessage);
}
