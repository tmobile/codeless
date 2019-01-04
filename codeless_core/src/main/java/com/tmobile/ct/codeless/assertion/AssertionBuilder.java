package com.tmobile.ct.codeless.assertion;

import com.tmobile.ct.codeless.core.Assertion;

/**
 * The Interface AssertionBuilder.
 *
 * @author Rob Graff
 */
public interface AssertionBuilder {

	/**
	 * Builds the.
	 *
	 * @param <T> the generic type
	 * @param type the type
	 * @return the assertion
	 */
	<T> Assertion<T> build(Class<T> type);
} 
