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
package com.tmobile.ct.codeless.test.extentreport;

import com.relevantcodes.extentreports.LogStatus;
import com.tmobile.ct.codeless.core.Result;
import com.tmobile.ct.codeless.core.Step;
import com.tmobile.ct.codeless.service.Call;
import com.tmobile.ct.codeless.service.core.ServiceCall;
import com.tmobile.ct.codeless.test.service.ServiceCallDTO;
import com.tmobile.ct.codeless.test.service.ServiceLogFilter;
import com.tmobile.ct.codeless.ui.UiStepImpl;
import com.tmobile.ct.codeless.ui.driver.WebDriverFactory;

/**
 * The Class TestStepReporter.
 *
 * @author Sai Chandra Korpu
 */
public class TestStepReporter {

	public static void reporter(Step step) throws Exception {
		try{
			if (step instanceof ServiceCall) {
				logServiceStepResult(step);

			} else {
				logUiStepResult(step);

			}
		}catch(Exception e){
			System.err.println("TestStepReporter Failure");
			e.printStackTrace();
		}
	}

	private static void logServiceStepResult(Step step) {

		if (step.getTest().getConfig().asMap().containsKey("logging.details.enabled")
				&& step.getTest().getConfig().get("logging.details.enabled").fullfill().equalsIgnoreCase("TRUE")) {

			ServiceCallDTO serviceCall = ServiceLogFilter.filter((ServiceCall) step);
			ExtentTestManager.getTest().log(logStepResult(step), step.getName(), getDOMResult(serviceCall));

		} else {
			ExtentTestManager.getTest().log(logStepResult(step), step.getName(), "");

		}
	}

	private static void logUiStepResult(Step step) throws Exception {

		if (logStepResult(step) == LogStatus.PASS) {
			ExtentTestManager.getTest().log(logStepResult(step), step.getName(), "");

		} else {
			String screenshotPath = WebDriverFactory.getScreenhot(step.getTest().getWebDriver(), step.getName());
			ExtentTestManager.getTest().log(LogStatus.FAIL, step.getName(),
					ExtentTestManager.getTest().addBase64ScreenShot(screenshotPath));
		}

	}

	private static String getDOMResult(ServiceCallDTO serviceCall) {
		int statusCode = serviceCall.getStatusCode();
		String requestBody = serviceCall.getRequestBody();
		String responseBody = serviceCall.getResponseBody();
		return "<div> <b>Request</b> : " + requestBody + "</div><div> <b>Response</b> : " + responseBody
				+ "</div><div> <b>Status Code</b> : " + statusCode + "</div>";

	}

	private static LogStatus logStepResult(Step step) {
		return step instanceof Call ? getLog(((Call) step).getResult()) : getLog(((UiStepImpl) step).getResult());

	}

	private static LogStatus getLog(Result result) {
		return result == Result.PASS ? LogStatus.PASS : LogStatus.FAIL;

	}

}
