package com.tmobile.ct.codeless.core;

/**
 * The Interface Trackable.
 *
 * @author Rob Graff
 */
public interface Trackable {

	/**
	 * Gets the result.
	 *
	 * @return the result
	 */
	Result getResult();
	
	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	Status getStatus();
	
	/**
	 * Sets the result.
	 *
	 * @param result the new result
	 */
	void setResult(Result result);
	
	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	void setStatus(Status status);
}
