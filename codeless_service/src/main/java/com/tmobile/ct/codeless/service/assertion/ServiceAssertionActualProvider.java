package com.tmobile.ct.codeless.service.assertion;

import com.tmobile.ct.codeless.service.core.ServiceCall;

/**
 * The Interface ServiceAssertionActualProvider.
 *
 * @author Rob Graff
 * @param <T> the generic type
 */
public interface ServiceAssertionActualProvider<T> {
	
	/**
	 * Gets the actual.
	 *
	 * @param call the call
	 * @return the actual
	 */
	T getActual(ServiceCall call);
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	String getName();

}
