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
package com.tmobile.ct.codeless.test.suite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.openqa.selenium.WebDriver;

import com.tmobile.ct.codeless.core.LogProxy;
import com.tmobile.ct.codeless.core.Result;
import com.tmobile.ct.codeless.core.Status;
import com.tmobile.ct.codeless.core.Step;
import com.tmobile.ct.codeless.core.Suite;
import com.tmobile.ct.codeless.core.Test;
import com.tmobile.ct.codeless.core.TestData;
import com.tmobile.ct.codeless.core.UiActionLog;

/**
 * The Class ExcelTest.
 *
 * @author Rob Graff
 */
public class TestImpl implements Test{

	private Long id;

	/** The name. */
	private String name;

	/** The steps. */
	private List<Step> steps = new ArrayList<>();

	/** The step map. */
	private Map<String, Step> stepMap = new HashMap<>();

	/** The config. */
	private Map<String, String> config = new HashMap<>();

	/** The suite. */
	private Suite suite;

	/** The driver. */
	private WebDriver driver;

	/** The test data. */
	private TestData testData;

	/** The log proxies. */
	private List<LogProxy> logProxies = new ArrayList<>();

	private Result result;

	private Status status;

	private List<UiActionLog> uiActionLog = new ArrayList<>();

	private boolean isTcdsData = false;

	private Map<String, String> data = new HashMap<>();

	private String errorMessage;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Result getResult() {
		return result;
	}

	@Override
	public Status getStatus() {
		return status;
	}

	@Override
	public void setResult(Result result) {
		this.result = result;
	}

	@Override
	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setSteps(List<Step> steps) {
		this.steps = steps;
		steps.forEach(step -> stepMap.put(step.getName().trim().toUpperCase(), step));
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public List<Step> getSteps() {
		return steps;
	}

	@Override
	public void setTestData(TestData data) {
		this.testData = data;
	}

	@Override
	public TestData getTestData() {

		return testData;
	}

	@Override
	public void addStep(Step step) {
		this.steps.add(step);
		this.stepMap.put(Optional.ofNullable(step.getName()).map(name -> name.trim().toUpperCase()).orElse("no name"), step);
	}

	@Override
	public void setConfig(Map<String, String> config) {
		this.config = config;
	}

	@Override
	public Map<String, String> getConfig() {
		return config;
	}

	@Override
	public Step getStepByName(String name) {
		return stepMap.get(name.toUpperCase());
	}

	@Override
	public void setSuite(Suite suite) {
		this.suite = suite;
	}

	@Override
	public Suite getSuite() {
		return suite;
	}

	@Override
	public void setWebDriver(WebDriver driver) {
		this.driver = driver;
	}

	@Override
	public WebDriver getWebDriver() {
		return driver;
	}

	@Override
	public void setLogProxies(List<LogProxy> logProxies) {
		this.logProxies = logProxies;
	}

	@Override
	public void addLogProxy(LogProxy logProxy) {
		this.logProxies.add(logProxy);
	}

	@Override
	public List<LogProxy> getLogProxies() {
		return logProxies;
	}

	@Override
	public void addSteps(List<Step> steps) {
		this.steps.addAll(steps);
	}

	@Override
	public void setUiActionLog(List<UiActionLog> uiActionLog) {
		this.uiActionLog = uiActionLog;
	}

	@Override
	public List<UiActionLog> getUiActionLog() {
		return uiActionLog;
	}

	@Override
	public void setTcdsData(boolean isTcds) {
		this.isTcdsData = isTcds;
	}

	@Override
	public boolean getTcdsData() {
		return isTcdsData;
	}

	@Override
	public Map<String, String> getReportingData() {
		return data;
	}

	@Override
	public void addReportingData(String key, String value) {
		this.data.put(key, value);
	}

	@Override
	public void setReportingData(Map<String, String> data) {
		this.data = data;
	}

	@Override
	public void removeReportingData(String key) {
		this.data.remove(key);
	}

	@Override
	public String getErrorMessage() {
		return errorMessage;
	}

	@Override
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
