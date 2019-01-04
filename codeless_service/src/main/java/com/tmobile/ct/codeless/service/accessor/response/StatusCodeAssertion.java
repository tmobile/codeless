package com.tmobile.ct.codeless.service.accessor.response;

import com.tmobile.ct.codeless.service.assertion.ServiceAssertionActualProvider;
import com.tmobile.ct.codeless.service.core.ServiceCall;

/**
 * The Class StatusCodeAssertion.
 *
 * @author Rob Graff
 */
public class StatusCodeAssertion implements ServiceAssertionActualProvider<Integer>{

	/**
	 * Instantiates a new status code assertion.
	 */
	public StatusCodeAssertion(){}
	
	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.assertion.ServiceAssertionActualProvider#getActual(com.tmobile.ct.codeless.service.core.ServiceCall)
	 */
	@Override
	public Integer getActual(ServiceCall call) {
		return call.getHttpResponse().getStatusCode();
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.assertion.ServiceAssertionActualProvider#getName()
	 */
	@Override
	public String getName() {
		return "Status Code";
	}

	

}
