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
package com.tmobile.ct.codeless.service.assertion;

import com.tmobile.ct.codeless.core.Assertion;
import com.tmobile.ct.codeless.service.core.ServiceCall;

/**
 * The Interface ServiceCallAssertion.
 *
 * @author Rob Graff
 * @param <T> the generic type
 */
public interface ServiceCallAssertion<T> extends Assertion<T>{

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Assertion#getActual()
	 */
	T getActual();
	
	/**
	 * Run.
	 *
	 * @param call the call
	 * @return the boolean
	 */
	Boolean run(ServiceCall call);
	
	/**
	 * Sets the service call.
	 *
	 * @param call the new service call
	 */
	void setServiceCall(ServiceCall call);
	
	/**
	 * Gets the service call.
	 *
	 * @return the service call
	 */
	ServiceCall getServiceCall();

}
