package com.tmobile.ct.codeless.core;

/**
 * The Interface Step.
 *
 * @author Rob Graff
 */
public interface Step extends Executable, Validatable{

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	String getName();
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	void setName(String name);
	
	
	/**
	 * Gets the step's parent component
	 * 
	 * @return the parent component
	 */
	Component getComponent();
	
	/**
	 * Set the step's parent component
	 * 
	 * @param parent component
	 */
	void setComponent(Component component);
	
	/**
	 * Gets the test.
	 *
	 * @return the test
	 */
	Test getTest();
	
	/**
	 * Sets the test.
	 *
	 * @param test the new test
	 */
	void setTest(Test test);
}
