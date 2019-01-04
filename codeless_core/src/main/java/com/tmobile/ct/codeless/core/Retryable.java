package com.tmobile.ct.codeless.core;

/**
 * The Interface Retryable.
 *
 * @author Rob Graff
 */
public interface Retryable {

	/**
	 * Sets the max retries.
	 *
	 * @param retries the new max retries
	 */
	void setMaxRetries(Integer retries);
	
	/**
	 * Gets the max retries.
	 *
	 * @return the max retries
	 */
	Integer getMaxRetries();
	
	/**
	 * Gets the retries.
	 *
	 * @return the retries
	 */
	Integer getRetries();
}
