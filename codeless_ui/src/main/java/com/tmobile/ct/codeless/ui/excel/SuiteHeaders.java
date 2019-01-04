package com.tmobile.ct.codeless.ui.excel;

import com.tmobile.ct.codeless.ui.excel.SuiteHeaders;

/**
 * The Enum SuiteHeaders.
 *
 * @author Rob Graff
 */
public enum SuiteHeaders {
	
	/** The step. */
	STEP,
	
	/** The action. */
	ACTION,
	
	/** The target. */
	TARGET,
	
	/** The input. */
	INPUT,
	
	/** The testdata. */
	TESTDATA;
	
	/**
	 * Instantiates a new suite headers.
	 */
	private SuiteHeaders() {

	}

	/**
	 * Parses the.
	 *
	 * @param header the header
	 * @return the suite headers
	 */
	public static SuiteHeaders parse(String header) {
		for (SuiteHeaders e : SuiteHeaders.values()) {
			if (e.name().equalsIgnoreCase(header)) {
				return e;
			}
		}
		return SuiteHeaders.TESTDATA;
	}
}


