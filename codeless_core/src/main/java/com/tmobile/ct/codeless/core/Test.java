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

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;

/**
 * The Interface Test.
 *
 * @author Rob Graff
 */
public interface Test extends Trackable{

	/**
	 * Gets the id.
	 * @return id
	 */
	Long getId ();

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	void setId (Long id);

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	void setName(String name);

	/**
	 * Sets the steps.
	 *
	 * @param steps the new steps
	 */
	void setSteps(List<Step> steps);

	/**
	 * Sets the test data.
	 *
	 * @param data the new test data
	 */
	void setTestData(TestData data);

	/**
	 * Adds the step.
	 *
	 * @param step the step
	 */
	void addStep(Step step);

	void addSteps(List<Step> steps);

	/**
	 * Sets the web driver.
	 *
	 * @param driver the new web driver
	 */
	void setWebDriver(WebDriver driver);

	/**
	 * Sets the log proxies.
	 *
	 * @param logProxies the new log proxies
	 */
	void setLogProxies(List<LogProxy> logProxies);

	/**
	 * Adds the log proxy.
	 *
	 * @param logProxy the log proxy
	 */
	void addLogProxy(LogProxy logProxy);

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	String getName();

	/**
	 * Gets the steps.
	 *
	 * @return the steps
	 */
	List<Step> getSteps();

	/**
	 * Gets the test data.
	 *
	 * @return the test data
	 */
	TestData getTestData();

	/**
	 * Gets the step by name.
	 *
	 * @param name the name
	 * @return the step by name
	 */
	Step getStepByName(String name);

	/**
	 * Sets the suite.
	 *
	 * @param suite the new suite
	 */
	void setSuite(Suite suite);

	/**
	 * Gets the suite.
	 *
	 * @return the suite
	 */
	Suite getSuite();

	/**
	 * Gets the web driver.
	 *
	 * @return the web driver
	 */
	WebDriver getWebDriver();

	/**
	 * Gets the log proxies.
	 *
	 * @return the log proxies
	 */
	List<LogProxy> getLogProxies();

	void setUiActionLog(List<UiActionLog> uiActionLog);

	List<UiActionLog> getUiActionLog();

	/**
	 * Gets the reporting data map.
	 *
	 * @return the reporting data map
	 */
	Map<String, String> getReportingData();

	/**
	 * Sets the reporting data map.
	 *
	 * @param data
	 */
	void setReportingData(Map<String, String> data);

	/**
	 * Sets the reporting data map.
	 * @param key
	 * @param value
	 */
	void addReportingData(String key, String value);

	/**
	 * Removes a reporting data entry from the map.
	 * @param key
	 */
	void removeReportingData(String key);

	/**
	 * Sets is test case data service supplied data.
	 * @param isTcds
	 */
	void setTcdsData(boolean isTcds);

	/**
	 * gets is test case data service supplied data.
	 * @return true or false
	 */
	boolean getTcdsData();

	/**
	 * Gets the set error message.
	 * @return  errorMessage
	 */
	String getErrorMessage();

	/**
	 * Sets an error message.
	 * @param errorMessage
	 */
	void setErrorMessage(String errorMessage);

	/**
	 * Sets the configuration data.
	 * @param config
	 */
	void setConfig(Map<String, String> config);

	/**
	 * Gets the configuration data.
	 * @return  config data
	 */
	Map<String, String> getConfig();

	/**
	 * Gets the console log file entity.
	 * @return  consoleLogFile.
	 */
	File getConsoleLogFile();

	/**
	 * Sets the console log file entity.
	 * @param  file entity
	 */
	void setConsoleLogFile(File file);


	/**
	 * Gets the startTime.
	 *
	 * @return the startTime
	 */
	Date getStartTime();

	/**
	 * Sets the startTime.
	 *
	 * @param startTime the new status
	 */
	void setStartTime(Date startTime);

	/**
	 * Gets the endTime.
	 *
	 * @return the endTime
	 */
	Date getEndTime();

	/**
	 * Sets the endTime.
	 *
	 * @param endTime the new status
	 */
	void setEndTime(Date endTime);
}
