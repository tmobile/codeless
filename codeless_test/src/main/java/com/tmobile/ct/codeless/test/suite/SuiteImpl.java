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
import java.util.UUID;

import com.tmobile.ct.codeless.core.Execution;
import com.tmobile.ct.codeless.core.Result;
import com.tmobile.ct.codeless.core.Status;
import com.tmobile.ct.codeless.core.Suite;
import com.tmobile.ct.codeless.core.Test;

/**
 * The Class ExcelSuite.
 *
 * @author Rob Graff
 */
public class SuiteImpl implements Suite{

	/** The name. */
	private String name;

	/** The config. */
	private Map<String, String> config;

	/** The tests. */
	private List<Test> tests;

	/** The test map. */
	private Map<String, Test> testMap = new HashMap<>();

	private Execution execution;

	private String id;

	/**
	 * Instantiates a new excel suite.
	 *
	 * @param name the name
	 */
	public SuiteImpl(String name){
		this.name = getSuiteName(name);
		this.config = new HashMap();
		this.tests = new ArrayList<>();
		this.id = UUID.randomUUID().toString();
	}

	private String getSuiteName(String name) {
		String[] parts = name.replace(".xlsx",  "").split("/");
		return parts[parts.length - 1];
	}
	
	@Override
	public void setName(String name) {
		this.name = name ;
	}

	@Override
	public void setTests(List<Test> tests) {
		this.tests = tests;
		tests.forEach(test -> testMap.put(test.getName().trim().toUpperCase(), test));
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public List<Test> getTests() {
		return tests;
	}

	@Override
	public Suite addTest(Test test) {
		tests.add(test);
		testMap.put(test.getName().trim().toUpperCase(), test);
		return this;
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
	public Test getTestByName(String name) {
		return testMap.get(name);
	}

	@Override
	public Result getResult() {
		return null;
	}

	@Override
	public Status getStatus() {
		return null;
	}

	@Override
	public void setResult(Result result) {

	}

	@Override
	public void setStatus(Status status) {

	}

	@Override
	public void setExecution(Execution execution) {
		this.execution = execution;
	}

	@Override
	public Execution getExecution() {
		return execution;
	}

	@Override
	public String getId() {
		return null;
	}

}
