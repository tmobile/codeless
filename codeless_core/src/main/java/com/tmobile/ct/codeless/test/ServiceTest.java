package com.tmobile.ct.codeless.test;

import java.util.Map;

/**
 * The Interface ServiceTest.
 *
 * @author Rob Graff
 */
public interface ServiceTest {

	/**
	 * Gets the model.
	 *
	 * @return the model
	 */
	String getModel();
	
	/**
	 * Gets the template.
	 *
	 * @return the template
	 */
	String getTemplate();
	
	/**
	 * Gets the test data.
	 *
	 * @return the test data
	 */
	Map<String,Map<String,String>> getTestData();
	
	/**
	 * Gets the expected status code.
	 *
	 * @return the expected status code
	 */
	Integer getExpectedStatusCode();
}
