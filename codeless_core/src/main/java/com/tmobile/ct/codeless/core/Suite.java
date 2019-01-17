package com.tmobile.ct.codeless.core;

import java.util.List;


/**
 * The Interface Suite.
 *
 * @author Rob Graff
 */
public interface Suite extends Trackable {

	String getId();
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	void setName(String name);
	
	/**
	 * Sets the tests.
	 *
	 * @param tests the new tests
	 */
	void setTests(List<Test> tests);
	
	/**
	 * Adds the test.
	 *
	 * @param test the test
	 * @return the suite
	 */
	Suite addTest(Test test);
	
	/**
	 * Sets the config.
	 *
	 * @param config the new config
	 */
	void setConfig(Config config);
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	String getName();
	
	/**
	 * Gets the tests.
	 *
	 * @return the tests
	 */
	List<Test> getTests();
	
	/**
	 * Gets the config.
	 *
	 * @return the config
	 */
	Config getConfig();
	
	/**
	 * Gets the test by name.
	 *
	 * @param name the name
	 * @return the test by name
	 */
	Test getTestByName(String name);
	
	void setExecution(Execution execution);
	
	Execution getExecution();
}
