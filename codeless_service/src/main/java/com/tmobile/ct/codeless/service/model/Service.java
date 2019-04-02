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

import java.util.List;

import com.tmobile.ct.codeless.service.httpclient.HttpMethod;

/**
 * The Interface Service.
 *
 * @author Rob Graff
 */
public interface Service {

	/**
	 * Gets the operation.
	 *
	 * @param method the method
	 * @param operation the operation
	 * @return the operation
	 */
	Operation getOperation(HttpMethod method, String operation);
	
	/**
	 * Adds the operation.
	 *
	 * @param operation the operation
	 */
	void addOperation(Operation operation);
	
	/**
	 * Sets the operations.
	 *
	 * @param operations the new operations
	 */
	void setOperations(List<Operation> operations);
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	String getName();
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	void setName(String name);
}
