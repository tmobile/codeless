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
package com.tmobile.ct.codeless.test.testng.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.tmobile.ct.codeless.core.Test;
import com.tmobile.ct.codeless.test.extentreport.ExtentTestManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The listener interface for receiving test events.
 * The class that is interested in processing a test
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addTestListener<code> method. When
 * the test event occurs, that object's appropriate
 * method is invoked.
 *
 * @author Rob Graff
 * @author Sai Chandra Korpu
 * @see TestEvent
 */
public class TestListener implements ITestListener {

	public static final Logger log = LoggerFactory.getLogger(TestListener.class);
	/**
	 * Gets the test method name.
	 *
	 * @param iTestResult the i test result
	 * @return the test method name
	 */
	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}

	/* (non-Javadoc)
	 * @see org.testng.ITestListener#onTestStart(org.testng.ITestResult)
	 */
	@Override
	public void onTestStart(ITestResult result) {
		log.debug("Entering TestListener.onTestStart method " + getTestMethodName(result) + " start");
		Test test = (Test) result.getParameters()[0];
		ExtentTestManager.startTest(test.getName(), "Test Start");

	}

	/* (non-Javadoc)
	 * @see org.testng.ITestListener#onTestSuccess(org.testng.ITestResult)
	 */
	@Override
	public void onTestSuccess(ITestResult result) {
		log.debug("Entering TestListener.onTestSuccess method " + getTestMethodName(result) + " succeed");
		// Extentreports log operation for passed tests.
		// ExtentTestManager.getTest().log(LogStatus.PASS, "Test passed");

	}

	/* (non-Javadoc)
	 * @see org.testng.ITestListener#onTestFailure(org.testng.ITestResult)
	 */
	@Override
	public void onTestFailure(ITestResult iTestResult) {

		// To add it in the extent report
		// logger.log(LogStatus.FAIL, logger.addScreenCapture(screenshotPath));

		/*
		 * log.debug("Entering TestListener.onTestFailure method " +
		 * getTestMethodName(iTestResult) + " failed");
		 * 
		 * // Get driver from BaseTest and assign to local webdriver variable. Object
		 * testClass = iTestResult.getInstance(); WebDriver webDriver = ((WebTest)
		 * testClass).getDriver();
		 * 
		 * // Take base64Screenshot screenshot. String base64Screenshot =
		 * "data:image/png;base64," + ((TakesScreenshot)
		 * webDriver).getScreenshotAs(OutputType.BASE64);
		 * 
		 * // Extentreports log and screenshot operations for failed tests.
		 * ExtentTestManager.getTest().log(LogStatus.FAIL, "Test Failed",
		 * ExtentTestManager.getTest().addBase64ScreenShot(base64Screenshot));
		 */

	}

	/* (non-Javadoc)
	 * @see org.testng.ITestListener#onTestSkipped(org.testng.ITestResult)
	 */
	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		log.debug("Entering TestListener.onTestSkipped method " + getTestMethodName(iTestResult) + " skipped!");
		// Extentreports log operation for skipped tests.
		// ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped");
		log.error("skip exception:: "+iTestResult.getThrowable());
		iTestResult.getThrowable().printStackTrace();
	}

	/* (non-Javadoc)
	 * @see org.testng.ITestListener#onTestFailedButWithinSuccessPercentage(org.testng.ITestResult)
	 */
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
		log.debug("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));

	}

	/* (non-Javadoc)
	 * @see org.testng.ITestListener#onStart(org.testng.ITestContext)
	 */
	@Override
	public void onStart(ITestContext context) {
		log.debug("Entering TestListener.onStart method " + context.getName());
//		context.setAttribute("WebDriver", this.getDriver());
		
	}

	/* (non-Javadoc)
	 * @see org.testng.ITestListener#onFinish(org.testng.ITestContext)
	 */
	@Override
	public void onFinish(ITestContext context) {
		log.debug("Entering TestListener.onFinish method " + context.getName());
		ExtentTestManager.endTest();
		ExtentTestManager.getReporter().flush();

	}

}
