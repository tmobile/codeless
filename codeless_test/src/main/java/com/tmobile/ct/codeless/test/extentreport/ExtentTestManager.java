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

import java.util.HashMap;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//import com.relevantcodes.extentreports.ExtentReports;
//import com.relevantcodes.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * The Class ExtentTestManager.
 *
 * @author Rob Graff
 * @author Sai Chandra Korpu
 */
public class ExtentTestManager {

	/** The extent. */
	static ExtentReports extent = getReporter();
	
	/** The extent test map. */
	static HashMap<Integer, ExtentTest> extentTestMap = new HashMap();

	/**
	 * Gets the reporter.
	 *
	 * @return the reporter
	 */
	public synchronized static ExtentReports getReporter() {
		if (extent == null) {
			// Set HTML reporting file location
			extent = new ExtentReports();
			ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/ExtentReport.html");
			spark.config().setTheme(Theme.STANDARD);
			//spark.config().setTimeStampFormat("MM/dd/yyyy HH:mm:ss");
			spark.config().setReportName("Codeless Report");
			spark.config().setEncoding("UTF-8");
			spark.config().setTimelineEnabled(true);
			extent.attachReporter(spark);
		}
		return extent;
	}

	/**
	 * Start test.
	 *
	 * @param testName the test name
	 * @param desc the desc
	 * @return the extent test
	 */
	public static synchronized ExtentTest startTest(String testName, String desc) {
		try {
			ExtentTest test = extent.createTest(testName, desc);
			extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
			return test;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * Gets the test.
	 *
	 * @return the test
	 */
	public static synchronized ExtentTest getTest() {
		return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
	}

	/**
	 * End test.
	 */
	public static synchronized void endTest() {
		//extent..endTest((ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId())));
		//getTest().info(MarkupHelper.createUnorderedList(extentTestMap).getMarkup());
	}

}
