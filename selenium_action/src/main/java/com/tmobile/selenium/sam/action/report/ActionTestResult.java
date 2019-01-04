/**
 * 
 */
package com.tmobile.selenium.sam.action.report;

import java.util.ArrayList;
import java.util.List;

import com.tmobile.selenium.sam.action.types.TestStatus;

/**
 * The Class ActionTestResult.
 *
 * @author Rob Graff (RGraff1)
 */
public class ActionTestResult {
	
	/** The test id. */
	private String testId;
	
	/** The name. */
	private String name;
	
	/** The step list. */
	private List<StepResult> stepList;
	
	/** The start time. */
	private long startTime;
	
	/** The end time. */
	private long endTime;
	
	/** The status. */
	private TestStatus status;
	


	/**
	 * Instantiates a new action test result.
	 */
	public ActionTestResult(){
		stepList = new ArrayList<StepResult>();
	}
	
	/**
	 * Start.
	 */
	public void start(){
		startTime = System.currentTimeMillis();
	}
	
	/**
	 * End.
	 *
	 * @param status the status
	 */
	public void end(TestStatus status){
		endTime = System.currentTimeMillis();
		this.status = status;
	}
	
	/**
	 * Adds the step.
	 *
	 * @param step the step
	 */
	public void addStep(StepResult step){
		stepList.add(step);
	}
	
	/**
	 * Gets the step.
	 *
	 * @param i the i
	 * @return the step
	 */
	public StepResult getStep(int i){
		return stepList.get(i);
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
	 * Gets the start time.
	 *
	 * @return the start time
	 */
	public long getStartTime() {
		return startTime;
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

}
