package com.tmobile.ct.codeless.test.excel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.openqa.selenium.WebDriver;

import com.tmobile.ct.codeless.core.Config;
import com.tmobile.ct.codeless.core.LogProxy;
import com.tmobile.ct.codeless.core.Result;
import com.tmobile.ct.codeless.core.Status;
import com.tmobile.ct.codeless.core.Step;
import com.tmobile.ct.codeless.core.Suite;
import com.tmobile.ct.codeless.core.Test;
import com.tmobile.ct.codeless.core.TestData;
import com.tmobile.ct.codeless.data.BasicConfig;

/**
 * The Class ExcelTest.
 *
 * @author Rob Graff
 */
public class ExcelTest implements Test{

	/** The name. */
	private String name;

	/** The steps. */
	private List<Step> steps = new ArrayList<>();

	/** The step map. */
	private Map<String,Step> stepMap = new HashMap<>();

	/** The config. */
	private Config config = new BasicConfig();

	/** The suite. */
	private Suite suite;

	/** The driver. */
	private WebDriver driver;

	/** The test data. */
	private TestData testData;

	/** The log proxies. */
	private List<LogProxy> logProxies = new ArrayList<>();

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Trackable#getResult()
	 */
	@Override
	public Result getResult() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Trackable#getStatus()
	 */
	@Override
	public Status getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Trackable#setResult(com.tmobile.ct.codeless.core.Result)
	 */
	@Override
	public void setResult(Result result) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Trackable#setStatus(com.tmobile.ct.codeless.core.Status)
	 */
	@Override
	public void setStatus(Status status) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Test#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Test#setSteps(java.util.List)
	 */
	@Override
	public void setSteps(List<Step> steps) {
		this.steps = steps;
		steps.forEach(step -> stepMap.put(step.getName().trim().toUpperCase(), step));
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Test#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Test#getSteps()
	 */
	@Override
	public List<Step> getSteps() {
		return steps;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Test#setTestData(com.tmobile.ct.codeless.core.TestData)
	 */
	@Override
	public void setTestData(TestData data) {
		this.testData = data;
	}

	/* (non-Javadoc)R
	 * @see com.tmobile.ct.codeless.core.Test#getTestData()
	 */
	@Override
	public TestData getTestData() {

		return testData;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Test#addStep(com.tmobile.ct.codeless.core.Step)
	 */
	@Override
	public void addStep(Step step) {
		this.steps.add(step);
		this.stepMap.put(Optional.ofNullable(step.getName()).map(name -> name.trim().toUpperCase()).orElse("no name"), step);
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Test#setConfig(com.tmobile.ct.codeless.core.Config)
	 */
	@Override
	public void setConfig(Config config) {
		this.config = config;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Test#getConfig()
	 */
	@Override
	public Config getConfig() {
		return config;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Test#getStepByName(java.lang.String)
	 */
	@Override
	public Step getStepByName(String name) {
		return stepMap.get(name.toUpperCase());
	}
	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Test#setSuite(com.tmobile.ct.codeless.core.Suite)
	 */
	@Override
	public void setSuite(Suite suite) {
		this.suite = suite;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Test#getSuite()
	 */
	@Override
	public Suite getSuite() {
		return suite;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Test#setWebDriver(org.openqa.selenium.WebDriver)
	 */
	@Override
	public void setWebDriver(WebDriver driver) {
		this.driver = driver;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Test#getWebDriver()
	 */
	@Override
	public WebDriver getWebDriver() {
		return driver;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Test#setLogProxies(java.util.List)
	 */
	@Override
	public void setLogProxies(List<LogProxy> logProxies) {
		this.logProxies = logProxies;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Test#addLogProxy(com.tmobile.ct.codeless.core.LogProxy)
	 */
	@Override
	public void addLogProxy(LogProxy logProxy) {
		this.logProxies.add(logProxy);
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Test#getLogProxies()
	 */
	@Override
	public List<LogProxy> getLogProxies() {
		return logProxies;
	}


}
