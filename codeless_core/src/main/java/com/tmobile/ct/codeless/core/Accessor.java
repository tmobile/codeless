package com.tmobile.ct.codeless.core;

public interface Accessor<T, U> {

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
