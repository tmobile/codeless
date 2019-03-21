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
package com.tmobile.ct.codeless.service.reference;

import com.tmobile.ct.codeless.core.Suite;
import com.tmobile.ct.codeless.service.core.ServiceCall;

/**
 * The Class CallRefBySuite.
 *
 * @author Rob Graff
 */
public class CallRefBySuite implements ServiceCallReference{

	/** The call name. */
	private String callName;
	
	/** The test name. */
	private String testName;
	
	/** The suite. */
	private Suite suite;

	/**
	 * Instantiates a new call ref by suite.
	 *
	 * @param suite the suite
	 * @param testName the test name
	 * @param callName the call name
	 */
	public CallRefBySuite(Suite suite, String testName, String callName){
		this.suite = suite;
		this.testName = testName;
		this.callName = callName;
	}
	
	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.reference.ServiceCallReference#find()
	 */
	@Override
	public ServiceCall find() {
		return (ServiceCall) suite.getTestByName(testName).getStepByName(callName);
	}

}
