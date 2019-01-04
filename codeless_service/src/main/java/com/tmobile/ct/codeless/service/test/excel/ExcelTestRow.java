package com.tmobile.ct.codeless.service.test.excel;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class ExcelTestRow.
 *
 * @author Rob Graff
 */
public class ExcelTestRow {

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

	/** The host override. */
	public String custom_host;

	/** The operation override. */
	public String custom_operation;

	/** The test data. */
	public List<String> testData = new ArrayList<>();

	/**
	 * Instantiates a new excel test row.
	 */
	public ExcelTestRow(){}
}
