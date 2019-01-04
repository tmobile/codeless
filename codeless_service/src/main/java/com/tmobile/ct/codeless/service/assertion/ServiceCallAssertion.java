package com.tmobile.ct.codeless.service.assertion;

import com.tmobile.ct.codeless.core.Assertion;
import com.tmobile.ct.codeless.service.core.ServiceCall;

/**
 * The Interface ServiceCallAssertion.
 *
 * @author Rob Graff
 * @param <T> the generic type
 */
public interface ServiceCallAssertion<T> extends Assertion<T>{

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Assertion#getActual()
	 */
	T getActual();
	
	/**
	 * Run.
	 *
	 * @param call the call
	 * @return the boolean
	 */
	Boolean run(ServiceCall call);
	
	/**
	 * Sets the service call.
	 *
	 * @param call the new service call
	 */
	void setServiceCall(ServiceCall call);
	
	/**
	 * Gets the service call.
	 *
	 * @return the service call
	 */
	ServiceCall getServiceCall();

}