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
package com.tmobile.ct.codeless.ui.build;

import com.tmobile.ct.codeless.ui.build.SuiteHeaders;

/**
 * The Enum SuiteHeaders.
 *
 * @author Rob Graff
 */
public enum SuiteHeaders {
	
	/** The step. */
	STEP,
	
	/** The action. */
	ACTION,
	
	/** The target. */
	TARGET,
	
	/** The input. */
	INPUT,
	
	/** The testdata. */
	TESTDATA,

	/** The description. */
	DESCRIPTION;
	
	/**
	 * Instantiates a new suite headers.
	 */
	private SuiteHeaders() {

	}

	/**
	 * Parses the.
	 *
	 * @param header the header
	 * @return the suite headers
	 */
	public static SuiteHeaders parse(String header) {
		for (SuiteHeaders e : SuiteHeaders.values()) {
			if (e.name().equalsIgnoreCase(header)) {
				return e;
			}
		}
		return SuiteHeaders.TESTDATA;
	}
}


