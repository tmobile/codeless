package com.tmobile.ct.codeless.service.reference;

import com.tmobile.ct.codeless.core.Suite;
import com.tmobile.ct.codeless.service.core.ServiceCall;

/**
 * The Class CallRefBySuite.
 *
 * @author Rob Graff
 */
public class CallRefBySuite implements ServiceCallReference{

	/** The call name. */
	private String callName;
	
	/** The test name. */
	private String testName;
	
	/** The suite. */
	private Suite suite;

	/**
	 * Instantiates a new call ref by suite.
	 *
	 * @param suite the suite
	 * @param testName the test name
	 * @param callName the call name
	 */
	public CallRefBySuite(Suite suite, String testName, String callName){
		this.suite = suite;
		this.testName = testName;
		this.callName = callName;
	}
	
	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.reference.ServiceCallReference#find()
	 */
	@Override
	public ServiceCall find() {
		return (ServiceCall) suite.getTestByName(testName).getStepByName(callName);
	}

}
