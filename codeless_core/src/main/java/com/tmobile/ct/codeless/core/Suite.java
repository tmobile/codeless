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

import java.util.List;
import java.util.Map;

/**
 * The Interface Suite.
 *
 * @author Rob Graff
 */
public interface Suite extends Trackable {

	String getId();

	void setId(String id);
	
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
	void setConfig(Map<String, String> config);
	
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
	Map<String, String> getConfig();
	
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
