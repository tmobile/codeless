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

import com.tmobile.ct.codeless.service.assertion.ServiceAssertionActualProvider;
import com.tmobile.ct.codeless.service.core.ServiceCall;

/**
 * The Class StatusCodeAssertion.
 *
 * @author Rob Graff
 */
public class StatusCodeAssertion implements ServiceAssertionActualProvider<Integer>{

	/**
	 * Instantiates a new status code assertion.
	 */
	public StatusCodeAssertion(){}
	
	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.assertion.ServiceAssertionActualProvider#getActual(com.tmobile.ct.codeless.service.core.ServiceCall)
	 */
	@Override
	public Integer getActual(ServiceCall call) {
		return call.getHttpResponse().getStatusCode();
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.assertion.ServiceAssertionActualProvider#getName()
	 */
	@Override
	public String getName() {
		return "Status Code";
	}

	

}
