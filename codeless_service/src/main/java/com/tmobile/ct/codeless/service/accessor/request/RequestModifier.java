package com.tmobile.ct.codeless.service.accessor.request;

import com.tmobile.ct.codeless.service.HttpRequest;
import com.tmobile.ct.codeless.service.accessor.response.ResponseAccessor;

/**
 * The Interface RequestModifier.
 *
 * @author Rob Graff
 * @param <T> the generic type
 */
public interface RequestModifier<T> {

	/**
	 * Sets the response accessor.
	 *
	 * @param responseAccessor the new response accessor
	 */
	void setResponseAccessor(ResponseAccessor<T> responseAccessor);
	
	/**
	 * Modify.
	 *
	 * @param request the request
	 */
	void modify(HttpRequest request);
}
