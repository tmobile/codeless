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
