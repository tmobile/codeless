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
package com.tmobile.ct.codeless.service.accessor.request;

import com.tmobile.ct.codeless.core.Accessor;
import com.tmobile.ct.codeless.service.assertion.ServiceAssertionActualProvider;
import com.tmobile.ct.codeless.service.core.ServiceCall;
import com.tmobile.ct.codeless.service.reference.ServiceCallReference;
import io.restassured.path.json.JsonPath;

/**
 * The Class JsonPathAccessor.
 *
 * @author Rob Graff
 * @param <T> the generic type
 */
public class JsonPathRequestAccessor<T> implements Accessor<T, ServiceCall>, ServiceAssertionActualProvider<T>{

	/** The json path. */
	private String jsonPath;

	/** The type class. */
	private Class<T> typeClass;

	/** The call ref. */
	private ServiceCallReference callRef;

	/** The call. */
	private ServiceCall call;

	/**
	 * Instantiates a new json path accessor.
	 *
	 * @param callRef the call ref
	 * @param jsonPath the json path
	 * @param typeClass the type class
	 */
	public JsonPathRequestAccessor(ServiceCallReference callRef, String jsonPath, Class<T> typeClass){
		this.jsonPath = jsonPath;
		this.typeClass = typeClass;
		this.callRef = callRef;
	}

	/**
	 * Instantiates a new json path accessor.
	 *
	 * @param jsonPath the json path
	 * @param typeClass the type class
	 */
	public JsonPathRequestAccessor(String jsonPath, Class<T> typeClass){
		this.jsonPath = jsonPath;
		this.typeClass = typeClass;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.assertion.ServiceAssertionActualProvider#getActual(com.tmobile.ct.codeless.service.core.ServiceCall)
	 */
	@Override
	public T getActual(ServiceCall call) {
		return new JsonPath(call.getHttpRequest().getBody().asString()).getObject(jsonPath, typeClass);
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.assertion.ServiceAssertionActualProvider#getName()
	 */
	@Override
	public String getName() {
		return "Response JSON: "+jsonPath;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.accessor.response.ResponseAccessor#read()
	 */
	@Override
	public T read() {
		return new JsonPath(this.call.getHttpRequest().getBody().asString()).getObject(jsonPath, typeClass);
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.accessor.response.ResponseAccessor#setServiceCall(com.tmobile.ct.codeless.service.core.ServiceCall)
	 */
	@Override
	public void setServiceCall(ServiceCall call) {
		this.call = call;
	}


	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.accessor.response.ResponseAccessor#getActual()
	 */
	@Override
	public String getActual() {
		return String.valueOf(getActual(this.callRef.find()));
	}

	@Override
	public String value() {
		return jsonPath;
	}

}
