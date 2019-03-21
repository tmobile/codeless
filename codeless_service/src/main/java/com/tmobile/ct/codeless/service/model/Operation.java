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
package com.tmobile.ct.codeless.service.model;

import com.tmobile.ct.codeless.service.HttpRequest;
import com.tmobile.ct.codeless.service.httpclient.HttpMethod;

/**
 * The Interface Operation.
 *
 * @author Rob Graff
 */
public interface Operation {

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	String getName();
	
	/**
	 * Gets the method.
	 *
	 * @return the method
	 */
	HttpMethod getMethod();
	
	/**
	 * Gets the path.
	 *
	 * @return the path
	 */
	String getPath();
	
	/**
	 * Gets the request.
	 *
	 * @return the request
	 */
	HttpRequest getRequest();
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	void setName(String name);
	
	/**
	 * Sets the method.
	 *
	 * @param method the new method
	 */
	void setMethod(HttpMethod method);
	
	/**
	 * Sets the path.
	 *
	 * @param path the new path
	 */
	void setPath(String path);
	
	/**
	 * Sets the request.
	 *
	 * @param request the new request
	 */
	void setRequest(HttpRequest request);
	
	/**
	 * Gets the key.
	 *
	 * @return the key
	 */
	OperationKey getKey();
	
	/**
	 * Gets the service.
	 *
	 * @return the service
	 */
	Service getService();
	
	/**
	 * Sets the service.
	 *
	 * @param service the new service
	 */
	void setService(Service service);
}
