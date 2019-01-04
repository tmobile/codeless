package com.tmobile.ct.codeless.test.extentreport;

import java.util.HashMap;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

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
			extent = new ExtentReports(System.getProperty("user.dir") + "/ExtentReport.html", true);

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
			ExtentTest test = extent.startTest(testName, desc);
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
		extent.endTest((ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId())));
	}

}
