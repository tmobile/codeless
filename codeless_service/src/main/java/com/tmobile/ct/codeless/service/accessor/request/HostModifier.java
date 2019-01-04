package com.tmobile.ct.codeless.service.accessor.request;

import com.tmobile.ct.codeless.service.HttpRequest;
import com.tmobile.ct.codeless.service.accessor.response.ResponseAccessor;
import com.tmobile.ct.codeless.service.httpclient.Host;

/**
 * The Class HostModifier.
 *
 * @author Rob Graff
 */
public class HostModifier implements RequestModifier<Host> {

	/** The response accessor. */
	private ResponseAccessor responseAccessor;

	/**
	 * Instantiates a new host modifier.
	 *
	 * @param responseAccessor the response accessor
	 */
	public HostModifier(ResponseAccessor responseAccessor) {

		this.responseAccessor = responseAccessor;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.accessor.request.RequestModifier#modify(com.tmobile.ct.codeless.service.HttpRequest)
	 */
	@Override
	public void modify(HttpRequest request) {
		request.setHost(new Host(responseAccessor.getActual()));

	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.accessor.request.RequestModifier#setResponseAccessor(com.tmobile.ct.codeless.service.accessor.response.ResponseAccessor)
	 */
	@Override
	public void setResponseAccessor(ResponseAccessor responseAccessor) {
		this.responseAccessor = responseAccessor;
	}
}
