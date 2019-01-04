package com.tmobile.ct.codeless.core;

/**
 * The Interface Failable.
 *
 * @author Rob Graff
 */
public interface Failable {

	/**
	 * Fail.
	 *
	 * @param e the e
	 */
	void fail(Throwable e);
	
	/**
	 * Gets the failure cause.
	 *
	 * @return the failure cause
	 */
	Throwable getFailureCause();
}
