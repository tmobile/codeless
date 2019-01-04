package com.tmobile.ct.codeless.core;

import java.util.List;

/**
 * The Interface Validatable.
 *
 * @author Rob Graff
 */
public interface Validatable {

	/**
	 * Gets the assertions.
	 *
	 * @return the assertions
	 */
	List<Assertion> getAssertions();
	
	/**
	 * Sets the assertions.
	 *
	 * @param assertions the new assertions
	 */
	void setAssertions(List<Assertion> assertions);
	
	/**
	 * Validate.
	 */
	void validate();
}
