package com.tmobile.ct.codeless.service.accessor.response;

import com.tmobile.ct.codeless.service.assertion.ServiceAssertionActualProvider;
import com.tmobile.ct.codeless.service.core.ServiceCall;

/**
 * The Class ResponseTimeAssertion.
 *
 * @author Rob Graff
 */
public class ResponseTimeAssertion implements ServiceAssertionActualProvider<Long>{

	/**
	 * Instantiates a new response time assertion.
	 */
	public ResponseTimeAssertion(){}
	
	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.assertion.ServiceAssertionActualProvider#getActual(com.tmobile.ct.codeless.service.core.ServiceCall)
	 */
	@Override
	public Long getActual(ServiceCall call) {
		return call.getHttpResponse().getResponseTime();
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.assertion.ServiceAssertionActualProvider#getName()
	 */
	@Override
	public String getName() {
		return "Response Time";
	}

}
