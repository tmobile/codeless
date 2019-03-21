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

import com.tmobile.ct.codeless.core.Test;
import com.tmobile.ct.codeless.service.core.ServiceCall;

/**
 * The Class CallRefByTest.
 *
 * @author Rob Graff
 */
public class CallRefByTest implements ServiceCallReference{

	/** The call name. */
	private String callName;
	
	/** The test. */
	private Test test;

	/**
	 * Instantiates a new call ref by test.
	 *
	 * @param test the test
	 * @param callName the call name
	 */
	public CallRefByTest(Test test, String callName){
		this.test = test;
		this.callName = callName;
	}
	
	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.reference.ServiceCallReference#find()
	 */
	@Override
	public ServiceCall find() {
		return (ServiceCall) test.getStepByName(callName);
	}

}
