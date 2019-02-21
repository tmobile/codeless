package com.tmobile.ct.codeless.test.excel;

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
import com.tmobile.ct.codeless.data.BasicSourcedDataMap;

/**
 * The Class ExcelSuite.
 *
 * @author Rob Graff
 */
public class ExcelSuite implements Suite{
	
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
	public ExcelSuite(String name){
		this.name = name;
		this.config = new BasicConfig();
		this.tests = new ArrayList<>();
		this.id = UUID.randomUUID().toString();
	}
	
	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Suite#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Suite#setTests(java.util.List)
	 */
	@Override
	public void setTests(List<Test> tests) {
		this.tests = tests;
		tests.forEach(test -> testMap.put(test.getName().trim().toUpperCase(), test));
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Suite#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Suite#getTests()
	 */
	@Override
	public List<Test> getTests() {
		return tests;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Suite#addTest(com.tmobile.ct.codeless.core.Test)
	 */
	@Override
	public Suite addTest(Test test) {
		tests.add(test);
		testMap.put(test.getName().trim().toUpperCase(), test);
		return this;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Suite#setConfig(com.tmobile.ct.codeless.core.Config)
	 */
	@Override
	public void setConfig(Config config) {
		this.config = config;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Suite#getConfig()
	 */
	@Override
	public Config getConfig() {
		return config;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Suite#getTestByName(java.lang.String)
	 */
	@Override
	public Test getTestByName(String name) {
		return testMap.get(name);
	}

	@Override
	public Result getResult() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Status getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setResult(Result result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setStatus(Status status) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		return null;
	}

}
