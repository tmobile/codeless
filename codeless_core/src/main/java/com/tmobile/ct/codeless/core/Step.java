/*******************************************************************************
 * * Copyright 2018 T Mobile, Inc. or its affiliates. All Rights Reserved.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 *  * use this file except in compliance with the License.  You may obtain a copy
 *  * of the License at
 *  *
 *  *   http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 *  * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 *  * License for the specific language governing permissions and limitations under
 *  * the License.
 ******************************************************************************/
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
}
