package com.tmobile.ct.codeless.testdata;

/**
 * The Interface ResponseAccessor.
 *
 * @author Rob Graff
 * @param <T> the generic type
 */
public interface ResponseAccessor<T, U> {

	/**
	 * Sets the service call.
	 *
	 * @param call the new service call
	 */
	void setServiceCall(U call);

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
