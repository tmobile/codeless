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
package com.tmobile.ct.codeless.ui.assertion;

import java.lang.reflect.Method;

/**
 * The Class UiAssertionMethod.
 *
 * @author Rob Graff
 */
public class UiAssertionMethod {
	
	/**
	 * Gets the assertion method.
	 *
	 * @param assertionMethodName the assertion method name
	 * @param expected the expected
	 * @return the assertion method
	 * @throws NoSuchMethodException the no such method exception
	 */
	public static Method getAssertionMethod(String assertionMethodName, String expected)
			throws NoSuchMethodException {
		if (expected == "true" || expected == "false") {
			return org.testng.Assert.class.getDeclaredMethod(assertionMethodName, boolean.class);
		} else if (expected.trim().length() == 0) {
			return org.testng.Assert.class.getDeclaredMethod(assertionMethodName, Object.class);
		} else {
			return org.testng.Assert.class.getDeclaredMethod(assertionMethodName, String.class, String.class);
		}
	}

}
