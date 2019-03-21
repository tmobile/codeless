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

import java.util.Optional;

import com.tmobile.ct.codeless.service.httpclient.HttpMethod;

/**
 * The Class OperationKey.
 *
 * @author Rob Graff
 */
public class OperationKey {

	/** The method. */
	private final HttpMethod method;
	
	/** The path. */
	private final String path;

	/**
	 * Instantiates a new operation key.
	 *
	 * @param method the method
	 * @param servicePath the service path
	 * @param operationPath the operation path
	 */
	public OperationKey(HttpMethod method, String servicePath, String operationPath){
		this.method = method;
		this.path = new StringBuilder().append(Optional.ofNullable(servicePath).orElse("")).append(Optional.ofNullable(operationPath).orElse("")).toString();
	}
	
	/**
	 * Instantiates a new operation key.
	 *
	 * @param method the method
	 * @param path the path
	 */
	public OperationKey(HttpMethod method, String path){
		this.method = method;
		this.path = path;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((method == null) ? 0 : method.hashCode());
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OperationKey other = (OperationKey) obj;
		if (method != other.method)
			return false;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		return true;
	}

}
