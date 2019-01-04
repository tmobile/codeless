package com.tmobile.ct.codeless.service.reference;

import com.tmobile.ct.codeless.core.Test;
import com.tmobile.ct.codeless.service.core.ServiceCall;

/**
 * The Class CallRefByTest.
 *
 * @author Rob Graff
 */
public class CallRefByTest implements ServiceCallReference{

	/** The call name. */
	private String callName;
	
	/** The test. */
	private Test test;

	/**
	 * Instantiates a new call ref by test.
	 *
	 * @param test the test
	 * @param callName the call name
	 */
	public CallRefByTest(Test test, String callName){
		this.test = test;
		this.callName = callName;
	}
	
	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.reference.ServiceCallReference#find()
	 */
	@Override
	public ServiceCall find() {
		return (ServiceCall) test.getStepByName(callName);
	}

}
