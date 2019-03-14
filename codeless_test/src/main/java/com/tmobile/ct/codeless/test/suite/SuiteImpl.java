package com.tmobile.ct.codeless.test.suite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.tmobile.ct.codeless.core.Config;
import com.tmobile.ct.codeless.core.Execution;
import com.tmobile.ct.codeless.core.Result;
import com.tmobile.ct.codeless.core.Status;
import com.tmobile.ct.codeless.core.Suite;
import com.tmobile.ct.codeless.core.Test;
import com.tmobile.ct.codeless.data.BasicConfig;

/**
 * The Class ExcelSuite.
 *
 * @author Rob Graff
 */
public class SuiteImpl implements Suite{

	/** The name. */
	private String name;

	/** The config. */
	private Config config;

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
		this.name = name;
		this.config = new BasicConfig();
		this.tests = new ArrayList<>();
		this.id = UUID.randomUUID().toString();
	}

	@Override
	public void setName(String name) {
		this.name = name;
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
	public void setConfig(Config config) {
		this.config = config;
	}

	@Override
	public Config getConfig() {
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