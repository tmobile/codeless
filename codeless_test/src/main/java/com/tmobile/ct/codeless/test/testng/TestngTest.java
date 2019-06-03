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
package com.tmobile.ct.codeless.test.testng;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.sun.org.apache.bcel.internal.generic.GOTO;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;

import com.google.common.base.Optional;

import com.tmobile.ct.codeless.core.Execution;
import com.tmobile.ct.codeless.core.Result;
import com.tmobile.ct.codeless.core.Status;
import com.tmobile.ct.codeless.core.Step;
import com.tmobile.ct.codeless.core.Suite;
import com.tmobile.ct.codeless.core.Test;
import com.tmobile.ct.codeless.core.TestData;
import com.tmobile.ct.codeless.core.config.Config;
import com.tmobile.ct.codeless.data.BasicTestData;
import com.tmobile.ct.codeless.service.Call;
import com.tmobile.ct.codeless.service.HttpRequest;
import com.tmobile.ct.codeless.test.BasicExecutor;
import com.tmobile.ct.codeless.test.ExecutionContainer;
import com.tmobile.ct.codeless.test.extentreport.ExtentTestManager;
import com.tmobile.ct.codeless.test.extentreport.TestStepReporter;
import com.tmobile.ct.codeless.ui.action.Click;
import com.tmobile.ct.codeless.ui.action.Type;
import com.tmobile.ct.codeless.ui.action.GoTo;
import com.tmobile.ct.codeless.ui.action.Select;
import com.tmobile.ct.codeless.ui.action.UiAction;
import com.tmobile.ct.codeless.ui.driver.WebDriverFactory;
import com.tmobile.ct.codeless.ui.UiStep;
import com.tmobile.selenium.sam.action.log.UiActionLogger;

/**
 * The Class TestngTest.
 *
 * @author Rob Graff
 * @author Sai Chandra Korpu
 */
@Listeners(com.tmobile.ct.codeless.test.testng.listeners.TestListener.class)
public class TestngTest {

	private static Logger log = LoggerFactory.getLogger(TestngTest.class);

	Suite suite;

	BasicExecutor executor = new BasicExecutor();

	Execution execution;

	private boolean enableUiActionLog = false;

	/**
	 * Suite setup.
	 *
	 * @param context the context
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@BeforeSuite
	public void beforeSuite(ITestContext context) throws IOException {

		File directory = null;
		if (!new File(System.getProperty("user.dir") + "/TestsScreenshots").exists()) {
			boolean created = new File(System.getProperty("user.dir") + "/TestsScreenshots").mkdir();
			if (created) {
				directory = new File(System.getProperty("user.dir") + "/TestsScreenshots");
			}
		} else {
			directory = new File(System.getProperty("user.dir") + "/TestsScreenshots");
			FileUtils.cleanDirectory(directory);
		}

		// get suite container from execution
		String id = context.getCurrentXmlTest().getParameter("codeless.suite.id");
		execution = ExecutionContainer.getExecution();
		suite = execution.getSuite(id);
		execution.getSuiteHooks().forEach(hook -> {
			hook.beforeSuite(suite);
		});

	}

	@AfterSuite
	public void afterSuite() {
		log.info("============================Test Suite Complete============================");
		Boolean success = true;
		for (Test test : suite.getTests()) {
			log.info("Test: [{}] - Result [{}]", test.getName(), test.getResult().toString());
			if (test.getResult() == Result.FAIL) {
				success = false;
			}
		}

		log.info("Test Suite Result: [{}]", success ? "PASS" : "FAIL");
		log.info("===========================================================================");

		execution.getSuiteHooks().forEach(hook -> {
			hook.afterSuite(suite);
		});
	}

	/**
	 * Codeless data provider - data driver spawn 1 method for each Test object in
	 * the Suite
	 *
	 * @return the object[][]
	 */
	@DataProvider(name = "codeless")
	public Object[][] codelessDataProvider() {

		if (suite == null || suite.getTests() == null || suite.getTests().size() == 0) {
			throw new RuntimeException("Invliad Test Suite, No Tests Found");
		}

		Object[][] data = new Object[suite.getTests().size()][1];
		int count = 0;
		for (Test test : suite.getTests()) {
			if (test != null) {
				data[count][0] = test;
				count++;
			}
		}
		return data;
	}

	/**
	 * Execute test.
	 *
	 * @param test the test
	 * @throws Exception the exception
	 */
	@org.testng.annotations.Test(dataProvider = "codeless" )
	public void executeTest(Test test) throws Exception {

		// before test
		test.setStatus(Status.IN_PROGRESS);
		test.setStartTime(new Date());
		log.info("Starting test [{}]", test.getName());
		execution.getTestHooks().forEach(hook -> {
			hook.beforeTest(test);
		});

		try {
			ExtentTestManager.getTest().setDescription(test.getName());
			Integer stepOrder = 1;

			// execute steps
			for (Step step : test.getSteps()) {
				step.setStartTime(new Date());
				step.setOrder(stepOrder++);
				execution.getStepHooks().forEach(hook -> {
					hook.beforeStep(step);
				});

				try {
					if (test.getResult() != null && test.getResult().equals(Result.FAIL)) {
						step.setResult(Result.SKIP);
						step.setStatus(Status.NO_RUN);
					} else {
						executor.run(step);
					}

					if (step.getResult().equals(Result.FAIL)) {
						test.setResult(Result.FAIL);
						test.setErrorMessage(step.getErrorMessage());
					}
				} catch (Exception e) {
					// step threw an unhandled exception, fail the step and the test case
					if (StringUtils.isNotBlank(test.getErrorMessage())) {
						test.setErrorMessage(e.getMessage());
					} else {
						test.setErrorMessage(test.getErrorMessage() + "\r\n" + e.getMessage());
					}

					if (StringUtils.isBlank(step.getErrorMessage())) {
						step.setErrorMessage(e.getMessage());
					} else {
						step.setErrorMessage(step.getErrorMessage() + "\r\n" + e.getMessage());
					}

					step.setResult(Result.FAIL);
					step.setStatus(Status.COMPLETE);
					test.setResult(Result.FAIL);
				} finally {
					step.setEndTime(new Date());
					TestStepReporter.reporter(step);
					execution.getStepHooks().forEach(hook -> {
						hook.afterStep(step);
					});
				}
			}

		} catch (Exception e) {
			// test level exception not handled below
			if (StringUtils.isBlank(test.getErrorMessage())) {
				test.setErrorMessage(e.getMessage());
			} else {
				test.setErrorMessage(test.getErrorMessage() + "\r\n" + e.getMessage());
			}

			// then fail the test
			test.setResult(Result.FAIL);
		} finally {
			test.setEndTime(new Date());

			// make sure any un-run steps are set to skipped result, no run status
			for (Step step : test.getSteps()) {
				if (step.getResult() == null) {
					step.setResult(Result.SKIP);
				}

				if (step.getStatus() == null) {
					step.setStatus(Status.NO_RUN);
				}

				if (step.getResult() == Result.FAIL && test.getResult() != Result.FAIL) {
					test.setResult(Result.FAIL);
				}
			}

			if (test.getWebDriver() != null) WebDriverFactory.teardown(test.getWebDriver());
			if (test.getResult() == null ||
				test.getResult() != Result.FAIL) {
				test.setResult(Result.PASS);
			}

			test.setStatus(Status.COMPLETE);
			if (test.getConfig().containsKey(Config.UI_ACTION_LOG_ENABLE)) {
				enableUiActionLog = Optional
						.fromNullable(
								Boolean.parseBoolean((String)test.getConfig().get(Config.UI_ACTION_LOG_ENABLE)))
						.or(false);
			}
			if (enableUiActionLog) {
				test.setUiActionLog(UiActionLogger.get());
			}

			UiActionLogger.destroy();

			createTestConsoleLogFile(test);

			// after test
			execution.getTestHooks().forEach(hook -> {
				hook.afterTest(test);
			});

			log.info("Test [{}] complete, result: [{}]", test.getName(), test.getResult().toString());

			String leaveFiles = test.getConfig().get(Config.LEAVE_CONSOLE_FILES);
			if (StringUtils.isNotBlank(leaveFiles) && Boolean.parseBoolean(leaveFiles) == true) {
				return;
			}

			test.getConsoleLogFile().delete();
		}
	}

	private void createTestConsoleLogFile(Test test) {
		// configured on test suite config page for all tests
		// by setting the "write.test.log" value
		if (!writeTestLog(test)) {
			return;
		}

		File consoleLogFile = populateConsoleLogFile(test);
		test.setConsoleLogFile(consoleLogFile.exists() ? consoleLogFile : null);
	}

	private boolean writeTestLog(Test test) {
		String writeTestLog = test.getConfig().get(Config.WRITE_TEST_LOG);

		return (StringUtils.isNotBlank(writeTestLog) && Boolean.parseBoolean(writeTestLog) == true);
	}

	private File populateConsoleLogFile(Test test) {
		File logFile = null;
		String fileName = test.getName() + "-" + new SimpleDateFormat("yyyy-MM-dd hh-mm-ss'.log'").format(new Date());
		StringBuilder consoleLog = new StringBuilder();
		consoleLog.append("Starting test: [" + test.getName() + "]").append("\r\n");
		consoleLog.append("Test Result: " + test.getResult().name()).append("\r\n");
		consoleLog.append("Test Status: " + test.getStatus().name()).append("\r\n");
		consoleLog.append("Config data:").append("\r\n");
		consoleLog.append(test.getConfig().toString()).append("\r\n");;
		consoleLog.append("Test data:").append("\r\n");;
		consoleLog.append(flattenTestData(test.getTestData())).append("\r\n");
		if (StringUtils.isNotBlank(test.getErrorMessage())) {
			consoleLog.append("Test Error Information: " + test.getErrorMessage()).append("\r\n");
		}
		for (Step step: test.getSteps()) {
			if (step instanceof Call) {
				getCallStepInformation(consoleLog, (Call)step);
			} else {
				try {
					getUiStepInformation(consoleLog, (UiStep) step);
				} catch (Exception ex) {
					consoleLog.append("Error processing step [" + step.getName() + "]");
				}
			}
		}

		// save data as a file and return it
        try {
            FileWriter fw = new FileWriter(fileName);
            fw.append(consoleLog.toString());
            fw.close();
            logFile = new File(fileName);
            log.info("Log file [{}] was created [{}], file size [{}]", fileName, logFile.exists(), logFile.exists() ? logFile.length() : 0);
        } catch (Exception ex) {
            log.error("Exception while writing console log file: [{}]", fileName);
        }

		return logFile;
	}

	private String flattenTestData(TestData testData) {
		Map<String, String> flattendMap = new HashMap<>();
		Set<String> keys = testData.asMap().keySet();
		for (String key : keys) {
			try {
				flattendMap.put(key, testData.get(key).fullfill().toString());
			} catch (Exception ex) {
				flattendMap.put(key, "error getting key value: " + ex.getMessage());
			}
		}

		return flattendMap.toString();

	}

	private void getCallStepInformation(StringBuilder consoleLog,  Call step) {
		consoleLog.append("\r\n================= Start Step =================").append("\r\n");
		try {
			getBaseStepLogInfo(consoleLog, (Step)step);
			HttpRequest request = step.getHttpRequest();
			consoleLog.append("Endpoint: " + getRequestEndPoint(request)).append("\r\n");
			consoleLog.append("HTTP Method: " + request.getHttpMethod().toString()).append("\r\n");
			if (request.getHeaders().size() > 0) {
				consoleLog.append("Call request headers: " + request.getHeaders().toString()).append("\r\n");
			}

			if (request.getForms() != null) {
				consoleLog.append("Call request form data: " + request.getForms().toString()).append("\r\n");
			}

			if (request.getMultiParts() != null) {
				consoleLog.append("Call request multipart data: " + request.getMultiParts().toString()).append("\r\n");
			}

			if (request.getBody() != null) {
				consoleLog.append("Call request body: " + request.getBody().asString()).append("\r\n");
			}

			if (step.getHttpResponse().getHeaders().size() > 0) {
				consoleLog.append("Call response headers: " + step.getHttpResponse().getHeaders().toString()).append("\r\n");
			}

			if (step.getHttpResponse().getBody() != null) {
				consoleLog.append("Call response body: " + step.getHttpResponse().getBody().asString()).append("\r\n");
			}

			consoleLog.append("Call response status code: " + step.getHttpResponse().getStatusCode()).append("\r\n");
			if (step.getFailureCause() != null) {
				consoleLog.append("Failure Cause: " + step.getFailureCause().getMessage()).append("\r\n");
			}
		} catch (Exception ex) {
			consoleLog.append("Exception getting step property: " + ex.getMessage()).append("\r\n");
		}

		consoleLog.append("================== End Step ==================").append("\r\n");
	}

	private String getRequestEndPoint(HttpRequest request) {
		String endPoint = request.getHost().getValue();
		if (request.getOperationPath() != null) {
			endPoint += request.getOperationPath().getValue();
		}

		if (request.getPathParams() != null) {
			endPoint = updatePathParams(request, endPoint);
		}

		if (request.getQueryParams() != null) {
			endPoint = setQueryParams(request, endPoint);
		}

		return endPoint;
	}

	private String setQueryParams(HttpRequest request, String endPoint) {
		Set<String> queryParamKeys = request.getQueryParams().keySet();
		if (queryParamKeys.size() > 0) {
			while (endPoint.endsWith("/")) {
				// removing any trailing slashes so we don't end up with parameters
				// immediately following a slash
				endPoint = StringUtils.removeEnd(endPoint, "/");
			}

			endPoint += "?";
		} else {
			return endPoint;
		}

		for (String key : queryParamKeys) {
			String keyValue = request.getQueryParams().get(key).getValues().get(0);
			endPoint += key + "=" + keyValue + "&";
		}

		while (endPoint.endsWith("&")) {
			// remove any trailing ampersands (&)
			endPoint = StringUtils.removeEnd(endPoint, "&");
		}

		return endPoint;
	}

	private String updatePathParams(HttpRequest request, String endPoint) {
		Set<String> pathParamKeys = request.getPathParams().keySet();
		if (pathParamKeys.size() > 0) {
			for (String key : pathParamKeys) {
				String keyValue = request.getPathParams().get(key).getValues().get(0);
				endPoint = endPoint.replace("{" + key + "}", keyValue);
			}
		}
		return endPoint;
	}

	private void getUiStepInformation(StringBuilder consoleLog,  UiStep step) {
		consoleLog.append("\r\n================= Start Step =================").append("\r\n");
		try {
			getBaseStepLogInfo(consoleLog, (Step)step);
			if (step.getAction() != null) {
				consoleLog.append("UI Action: " + getActionName(step.getAction())).append("\r\n");
			}

			consoleLog.append("UI Action Target: " + getTarget(step)).append("\r\n");

			consoleLog.append("Webdriver Info: " + step.getTest().getWebDriver().toString()).append("\r\n");
			if (StringUtils.isNotBlank(step.getScreenShotPath())) {
				consoleLog.append("Screenshot Path: " + step.getScreenShotPath()).append("\r\n");
			}

		} catch (Exception ex) {
			consoleLog.append("Exception getting step property: " + ex.getMessage()).append("\r\n");
		}

		consoleLog.append("================== End Step ==================").append("\r\n");
	}

	private String getActionName(UiAction action) {
		String actionName = action.toString();
		actionName = actionName.substring(actionName.lastIndexOf(".") + 1);

		return actionName.substring(0, actionName.indexOf("@"));
	}

	private String getTarget(UiStep step) {
		if (StringUtils.isNotBlank(step.getTarget())) {
			return step.getTarget()
					+ (StringUtils.isNotBlank(step.getAction().getText())
					? " entry value: " + populatePlaceholders(step)
					: "");
		}

		return populatePlaceholders(step);
	}

	private String populatePlaceholders(UiStep step) {
		UiAction action = step.getAction();
		String actionText = action.getText();
		if (actionText.contains("{{")) {
			Map<String, String> configData = step.getTest().getConfig();
			for (String key : configData.keySet()) {
				String replaceMe = "{{" + key + "}}";
				if (actionText.contains(replaceMe)) {
					actionText = actionText.replace(replaceMe, configData.get(key));
				}
			}

			if (actionText.contains("{{")) {
				Map<String, String> testData = step.getTest().getTestData().asMap();
				for (String key : testData.keySet()) {
					String replaceMe = "{{" + key + "}}";
					if (actionText.contains(replaceMe)) {
						actionText = actionText.replace(replaceMe, testData.get(key));
					}
				}
			}

		}

		return actionText;
	}

	private void getBaseStepLogInfo(StringBuilder consoleLog, Step step) {
		consoleLog.append("Step Name: " + step.getName()).append("\r\n");
		consoleLog.append("Step Description: " + step.getDescription()).append("\r\n");
		consoleLog.append("Step Order: " + step.getOrder()).append("\r\n");
		consoleLog.append("Step Result: " + step.getResult().toString()).append("\r\n");
		consoleLog.append("Step Status: " + step.getStatus().toString()).append("\r\n");
		consoleLog.append("Step Start Time: " + step.getStartTime().toString()).append("\r\n");
		consoleLog.append("Step Duration (in milliseconds): " + (step.getEndTime().getTime() - step.getStartTime().getTime())).append("\r\n");
		consoleLog.append("Attempts: ").append(step.getRetries() == null ? 1 : (step.getRetries() + 1)).append(" of a max ").append(step.getMaxRetries() == null ?  1 : (step.getMaxRetries() + 1)).append("\r\n");
		if (StringUtils.isNotBlank(step.getErrorMessage())) {
			consoleLog.append("Step Error Information: " + step.getErrorMessage()).append("\r\n");
		}
	}
}
