package com.tmobile.ct.codeless.testdata;

/**
 * The Interface RequestModifier.
 *
 * @author Rob Graff
 * @param <T> the generic type
 */
public interface RequestModifier<T,U> {

	/**
	 * Sets the response accessor.
	 *
	 * @param responseAccessor the new response accessor
	 */
	//void setResponseAccessor(ResponseAccessor<T,U> responseAccessor);

	/**
	 * Modify.
	 *
	 * @param request the request
	 */
	void modify(U request);
}
