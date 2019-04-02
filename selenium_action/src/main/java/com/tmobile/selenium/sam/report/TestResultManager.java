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
package com.tmobile.selenium.sam.report;

/**
 * The Class TestResultManager.
 *
 * @author Rob Graff (RGraff1)
 */
public class TestResultManager {
	
	/** The test result. */
	private static ThreadLocal<TestResult> testResult = new ThreadLocal<TestResult>();
	
	/**
	 * Instantiates a new test result manager.
	 */
	private TestResultManager(){}

	/**
	 * Gets the test result.
	 *
	 * @return the test result
	 */
	public static TestResult getTestResult(){
		if(null == testResult.get()){
			initTestResult();
		}
		return testResult.get();
	}
	
	/**
	 * Inits the test result.
	 */
	private static void initTestResult() {
		TestResult result = new TestResult();
		result.setStartTime(System.currentTimeMillis());
		setTestResult(result);
	}

	/**
	 * Sets the test result.
	 *
	 * @param newResult the new test result
	 */
	public static void setTestResult(TestResult newResult){
		testResult.set(newResult);
	}
}
