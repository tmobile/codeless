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
package com.tmobile.ct.codeless.service.accessor.response;

import java.util.concurrent.ExecutionException;

import com.tmobile.ct.codeless.core.Accessor;
import com.tmobile.ct.codeless.service.assertion.ServiceAssertionActualProvider;
import com.tmobile.ct.codeless.service.core.ServiceCall;
import com.tmobile.ct.codeless.service.reference.ServiceCallReference;

/**
 * The Class BodyStringAccessor.
 *
 * @author Rob Graff
 */
public class BodyStringAccessor implements Accessor<String, ServiceCall>, ServiceAssertionActualProvider<String>{

	/** The call ref. */
	private ServiceCallReference callRef;

	/** The call. */
	private ServiceCall call;

	/**
	 * Instantiates a new body string accessor.
	 *
	 * @param callRef the call ref
	 */
	public BodyStringAccessor(ServiceCallReference callRef){
		this.callRef = callRef;
	}

	/**
	 * Instantiates a new body string accessor.
	 */
	public BodyStringAccessor(){}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.assertion.ServiceAssertionActualProvider#getActual(com.tmobile.ct.codeless.service.core.ServiceCall)
	 */
	@Override
	public String getActual(ServiceCall call) {
		return call.getHttpResponse().getBody().asString();
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.assertion.ServiceAssertionActualProvider#getName()
	 */
	@Override
	public String getName() {
		return "Full Response Body";
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.accessor.response.ResponseAccessor#read()
	 */
	@Override
	public String read() {
		return this.call.getHttpResponse().getBody().asString();
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
		ServiceCall call = this.callRef.find();

		try {
			call.isComplete().get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}

		return getActual(call);
	}

	@Override
	public String value() {
		// TODO Auto-generated method stub
		return null;
	}
}
