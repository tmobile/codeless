/**
 * 
 */
package com.tmobile.selenium.sam.action.report;

import java.util.Optional;

import com.tmobile.selenium.sam.action.types.StepStatus;

/**
 * The Class StepResult.
 *
 * @author Rob Graff (RGraff1)
 */
public class StepResult {
	
	/** The status. */
	private StepStatus status;
	
	/** The exception. */
	private Exception exception;
	
	/** The screenshot location. */
	private String screenshotLocation;
	
	/** The start time. */
	private long startTime;
	
	/** The end time. */
	private long endTime;

	/**
	 * Instantiates a new step result.
	 *
	 * @param status the status
	 */
	public StepResult(StepStatus status){
		this.status = status;
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
	public void end(StepStatus status){
		endTime = System.currentTimeMillis();
		this.status = status;
	}
	
	/**
	 * Gets the step status.
	 *
	 * @return the step status
	 */
	public StepStatus getStepStatus(){
		return status;
	}
	
	/**
	 * Gets the step bool.
	 *
	 * @return the step bool
	 */
	public boolean getStepBool(){
		if(status == StepStatus.pass){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Sets the exception.
	 *
	 * @param e the new exception
	 */
	public void setException(Exception e){
		this.exception = e;
	}
	
	/**
	 * Gets the exception.
	 *
	 * @return the exception
	 */
	public Exception getException(){
		return exception;
	}
	
	/**
	 * Gets the exception string.
	 *
	 * @return the exception string
	 */
	public String getExceptionString(){
		if(null != exception){
			return exception.toString();
		}else{
			return "";
		}
	}

	/**
	 * Gets the screenshot location.
	 *
	 * @return the screenshot location
	 */
	public String getScreenshotLocation() {
		return screenshotLocation;
	}

	/**
	 * Sets the screenshot location.
	 *
	 * @param screenshotLocation the new screenshot location
	 */
	public void setScreenshotLocation(String screenshotLocation) {
		this.screenshotLocation = screenshotLocation;
	}

	/**
	 * Gets the time.
	 *
	 * @return the time
	 */
	public long getTime(){
		return endTime - startTime;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StepResult [status=" + status + ", exception=" + exception + ", screenshotLocation="
				+ screenshotLocation + ", startTime=" + startTime + ", endTime=" + endTime + "]";
	}
	
	
}
