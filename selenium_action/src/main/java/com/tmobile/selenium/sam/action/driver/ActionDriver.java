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
package com.tmobile.selenium.sam.action.driver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tmobile.selenium.sam.action.actions.Action;
import com.tmobile.selenium.sam.action.actions.IAction;
import com.tmobile.selenium.sam.action.report.StepResult;
import com.tmobile.selenium.sam.action.types.TestStatus;
import com.tmobile.selenium.sam.report.TestResult;
import com.tmobile.selenium.sam.report.TestResultManager;

/**
 * The Class ActionDriver.
 *
 * @author Rob Graff
 */
public class ActionDriver {
	
	/** The log. */
	private static Logger log = LoggerFactory.getLogger(ActionDriver.class);
	
	/** The test result. */
	private TestResult testResult = TestResultManager.getTestResult();
	
	/**
	 * Instantiates a new action driver.
	 */
	public ActionDriver(){
		
	}

	/**
	 * Run.
	 *
	 * @param action the action
	 */
	public void run(Action action) {
		try{
			action.execute();
			StepResult result = action.getStepResult();
			log.info("action[" + action.type() + "] status[" + result.getStepStatus().name() + "] time["
					+ result.getTime() + "] " + action.toString() + " screenshot[" + result.getScreenshotLocation()
					+ "]");
			testResult.addStepResult(result);
			if (!result.getStepBool() && !action.isOptional()) {
				log.info(result.getExceptionString());
				testResult.setStatus(TestStatus.fail);
				testResult.end();
				throw result.getException();
			}else if(!result.getStepBool() && action.isOptional()){
				log.info("optional action failed with exception[ "+result.getExceptionString()+" ]");
			}
		}catch(Throwable ex){
			FailedActionException fae = new FailedActionException();
			fae.initCause(ex);
			throw fae;
		}
	}
}
