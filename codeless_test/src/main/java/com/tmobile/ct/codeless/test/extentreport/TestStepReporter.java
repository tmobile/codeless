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

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.google.common.base.Optional;
//import com.relevantcodes.extentreports.LogStatus;
import com.tmobile.ct.codeless.core.Result;
import com.tmobile.ct.codeless.core.Step;
import com.tmobile.ct.codeless.service.Call;
import com.tmobile.ct.codeless.core.config.Config;
import com.tmobile.ct.codeless.service.core.ServiceCall;
import com.tmobile.ct.codeless.test.service.ServiceCallDTO;
import com.tmobile.ct.codeless.test.service.ServiceLogFilter;
import com.tmobile.ct.codeless.ui.UiStep;
import com.tmobile.ct.codeless.ui.UiStepImpl;
import com.tmobile.ct.codeless.ui.driver.WebDriverFactory;

import java.util.Map;

/**
 * The Class TestStepReporter.
 *
 * @author Sai Chandra Korpu
 */
public class TestStepReporter {

	private static final Logger logger = LoggerFactory.getLogger(TestStepReporter.class);

	private static boolean loggingEnabled = false;

	public static void reporter(Step step) throws Exception {
		try{
			if (step instanceof ServiceCall) {
				logServiceStepResult(step);

			} else {
				logUiStepResult((UiStep)step);

			}
		}catch(Exception e){
			logger.error("TestStepReporter Failure {}",e.getStackTrace().toString());
		}
	}

	private static void logServiceStepResult(Step step) {

		Status status = logStepResult(step);
		Map<String, String> testConfig = step.getTest().getConfig();
		if (testConfig.containsKey(Config.LOGGING_DETAILS_ENABLED)) {
			loggingEnabled = Boolean
							.parseBoolean((String)testConfig.get(Config.LOGGING_DETAILS_ENABLED));
		}
		if (loggingEnabled) {
			ServiceCallDTO serviceCall = ServiceLogFilter.filter((ServiceCall) step);
			ExtentTestManager.getTest().log(status, getDOMResult(serviceCall));
		} else {
			ExtentTestManager.getTest().log(status, step.getName());
		}
	}

	private static void logUiStepResult(UiStep step) throws Exception {

		String screenshotPath = "";
		Status status = logStepResult(step);
		if (step.getTest().getConfig().containsKey(Config.TEST_SCREENSHOT_POLICY)) {
			 String screenShotPolicy = Optional
					.fromNullable((String)step.getTest().getConfig().get(Config.TEST_SCREENSHOT_POLICY))
					.or(Config.EMPTY);

			String actionName = step.getAction().getClass().getSimpleName();

			if (!actionName.equalsIgnoreCase("close") && status != Status.SKIP) {

				if (screenShotPolicy.equalsIgnoreCase(Config.ALL_STEPS)) {
					screenshotPath = WebDriverFactory.getScreenhot(step.getTest().getWebDriver(), step.getName());
					step.setScreenShotPath(screenshotPath);
				} else if (screenShotPolicy.equalsIgnoreCase(Config.FAILURE_ONLY) && status == Status.FAIL) {
					screenshotPath = WebDriverFactory.getScreenhot(step.getTest().getWebDriver(), step.getName());
					step.setScreenShotPath(screenshotPath);
				}
			}
		}

		if (StringUtils.isNotBlank(screenshotPath)) {
			ExtentTestManager.getTest().log(status, step.getName(),
					MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		} else {
			ExtentTestManager.getTest().log(status, step.getName());
		}
	}

	private static String getDOMResult(ServiceCallDTO serviceCall) {
		int statusCode = 0;
		try {
			statusCode = serviceCall.getStatusCode();
		} catch (NullPointerException npex) {
			logger.error(npex.getMessage());
			logger.error("Setting status code to [400]");

			statusCode = 400;
		}

		String requestBody = serviceCall.getRequestBody();
		String responseBody = serviceCall.getResponseBody();
		return "<div> <b>Request</b> : " + requestBody + "</div><div> <b>Response</b> : " + responseBody
				+ "</div><div> <b>Status Code</b> : " + statusCode + "</div>";

	}

	private static Status logStepResult(Step step) {
		return step instanceof Call ? getLog(((Call) step).getResult()) : getLog(((UiStepImpl) step).getResult());

	}

	private static Status getLog(Result result) {
		switch (result) {
		case PASS:
			return Status.PASS;
		case FAIL:
			return Status.FAIL;
		case SKIP:
			return Status.SKIP;
		}
		return null;
	}

}
