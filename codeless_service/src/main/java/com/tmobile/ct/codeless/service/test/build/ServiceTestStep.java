package com.tmobile.ct.codeless.service.test.build;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class ExcelTestRow.
 *
 * @author Rob Graff
 */
public class ServiceTestStep {

	/** The test name. */
	public String testName;

	/** The service. */
	public String service;

	/** The method. */
	public String method;

	/** The operation. */
	public String operation;

	/** The body template. */
	public String bodyTemplate;

	/** The expected status. */
	public String expectedStatus;

	/** The test data. */
	public List<String> testData = new ArrayList<>();

	/**
	 * Instantiates a new excel test row.
	 */
	public ServiceTestStep(){}
}
