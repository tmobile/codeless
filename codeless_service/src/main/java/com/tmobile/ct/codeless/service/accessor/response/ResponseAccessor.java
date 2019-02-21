package com.tmobile.ct.codeless.service.accessor.response;

import com.tmobile.ct.codeless.service.core.ServiceCall;

/**
 * The Interface ResponseAccessor.
 *
 * @author Rob Graff
 * @param <T> the generic type
 */
public interface ResponseAccessor<T> {
	
	/**
	 * Sets the service call.
	 *
	 * @param call the new service call
	 */
	void setServiceCall(ServiceCall call);
	
	/**
	 * Read.
	 *
	 * @return the t
	 */
	T read();
	
	/**
	 * Gets the actual.
	 *
	 * @return the actual
	 */
	String getActual();
}
