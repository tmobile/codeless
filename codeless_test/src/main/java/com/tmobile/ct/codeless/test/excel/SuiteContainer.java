package com.tmobile.ct.codeless.test.excel;

import com.tmobile.ct.codeless.core.Suite;

/**
 * The Class SuiteContainer.
 *
 * @author Rob Graff
 */
public class SuiteContainer {

	/** The suite. */
	private static Suite suite;
	
	/**
	 * Instantiates a new suite container.
	 */
	private SuiteContainer(){}
	
	/**
	 * Sets the suite.
	 *
	 * @param newSuite the new suite
	 */
	public static void setSuite(Suite newSuite){
		suite = newSuite;
	}
	
	/**
	 * Gets the suite.
	 *
	 * @return the suite
	 */
	public static Suite getSuite(){
		return suite;
	}
}
