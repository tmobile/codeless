package com.tmobile.selenium.sam.report;

import java.util.ArrayList;
import java.util.List;

import com.tmobile.selenium.sam.action.report.StepResult;
import com.tmobile.selenium.sam.action.types.TestStatus;

/**
 * The Class TestResult.
 *
 * @author Rob Graff
 */
public class TestResult {
	
	/** The name. */
	private String name;
	
	/** The result. */
	private ResultType result;
	
	/** The alm result. */
	private String almResult;
	
	/** The method. */
	private String method;
	
	/** The test id. */
	private String testId;
	
	/** The duration. */
	private long duration;
	
	/** The start time. */
	private long startTime;
	
	/** The end time. */
	private long endTime;
	
	/** The status. */
	private TestStatus status;
	
	/** The browser. */
	private String browser;
	
	/** The step list. */
	private List<StepResult> stepList = new ArrayList<StepResult>();

	/**
	 * Instantiates a new test result.
	 */
	public TestResult(){
		
	}

	/**
	 * Gets the result.
	 *
	 * @return the result
	 */
	public ResultType getResult() {
		return result;
	}

	/**
	 * Sets the result.
	 *
	 * @param result the new result
	 */
	public void setResult(ResultType result) {
		this.result = result;
	}

	/**
	 * Gets the method.
	 *
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * Sets the method.
	 *
	 * @param method the new method
	 */
	public void setMethod(String method) {
		this.method = method;
	}

	/**
	 * Gets the test id.
	 *
	 * @return the test id
	 */
	public String getTestId() {
		return testId;
	}

	/**
	 * Sets the test id.
	 *
	 * @param testId the new test id
	 */
	public void setTestId(String testId) {
		this.testId = testId;
	}
	
	/**
	 * Gets the alm result.
	 *
	 * @return the alm result
	 */
	public String getAlmResult() {
		return almResult;
	}

	/**
	 * Sets the alm result.
	 *
	 * @param almResult the new alm result
	 */
	public void setAlmResult(String almResult) {
		this.almResult = almResult;
	}

	/**
	 * Gets the duration.
	 *
	 * @return the duration
	 */
	public long getDuration() {
		return duration;
	}

	/**
	 * Sets the duration.
	 *
	 * @param duration the new duration
	 */
	public void setDuration(long duration) {
		this.duration = duration;
	}
	
	

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the start time.
	 *
	 * @return the start time
	 */
	public long getStartTime() {
		return startTime;
	}

	/**
	 * Sets the start time.
	 *
	 * @param startTime the new start time
	 */
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	/**
	 * Gets the end time.
	 *
	 * @return the end time
	 */
	public long getEndTime() {
		return endTime;
	}

	/**
	 * Sets the end time.
	 *
	 * @param endTime the new end time
	 */
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public TestStatus getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(TestStatus status) {
		this.status = status;
	}

	/**
	 * Gets the browser.
	 *
	 * @return the browser
	 */
	public String getBrowser() {
		return browser;
	}

	/**
	 * Sets the browser.
	 *
	 * @param browser the new browser
	 */
	public void setBrowser(String browser) {
		this.browser = browser;
	}

	/**
	 * Gets the step list.
	 *
	 * @return the step list
	 */
	public List<StepResult> getStepList() {
		return stepList;
	}

	/**
	 * Sets the step list.
	 *
	 * @param stepList the new step list
	 */
	public void setStepList(List<StepResult> stepList) {
		this.stepList = stepList;
	}
	
	/**
	 * Adds the step result.
	 *
	 * @param result the result
	 */
	public void addStepResult(StepResult result){
		this.stepList.add(result);
	}
	
	/**
	 * End.
	 */
	public void end(){
		endTime = System.currentTimeMillis();
		if(this.status != TestStatus.fail){
			this.status = TestStatus.pass;
		}
	}
	
	/**
	 * Gets the run time.
	 *
	 * @return the run time
	 */
	public long getRunTime(){
		return endTime - startTime;
	}
	
	/**
	 * Gets the run time string.
	 *
	 * @return the run time string
	 */
	public String getRunTimeString(){
		long millis = endTime - startTime;
		long second = (millis / 1000) % 60;
		long minute = (millis / (1000 * 60)) % 60;
		long hour = (millis / (1000 * 60 * 60)) % 24;
		millis = millis % 1000;

		return String.format("%02d:%02d:%02d:%d", hour, minute, second, millis);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TestResult [name=" + name + ", result=" + result + ", almResult=" + almResult + ", method=" + method
				+ ", testId=" + testId + ", duration=" + duration + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", status=" + status + ", browser=" + browser + ", stepList=" + stepList + "]";
	}


	
	
}
