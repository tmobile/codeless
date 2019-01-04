package com.tmobile.ct.codeless.service.accessor.request;

import com.tmobile.ct.codeless.service.HttpRequest;
import com.tmobile.ct.codeless.service.accessor.response.ResponseAccessor;
import com.tmobile.ct.codeless.service.httpclient.OperationPath;

/**
 * The Class OperationPathModifier.
 *
 * @author Rob Graff
 */
public class OperationPathModifier implements RequestModifier<OperationPath> {

	/** The response accessor. */
	private ResponseAccessor responseAccessor;

	/**
	 * Instantiates a new operation path modifier.
	 *
	 * @param responseAccessor the response accessor
	 */
	public OperationPathModifier(ResponseAccessor responseAccessor) {

		this.responseAccessor = responseAccessor;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.accessor.request.RequestModifier#modify(com.tmobile.ct.codeless.service.HttpRequest)
	 */
	@Override
	public void modify(HttpRequest request) {
		request.setOperationPath(new OperationPath(responseAccessor.getActual()));

	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.accessor.request.RequestModifier#setResponseAccessor(com.tmobile.ct.codeless.service.accessor.response.ResponseAccessor)
	 */
	@Override
	public void setResponseAccessor(ResponseAccessor responseAccessor) {
		this.responseAccessor = responseAccessor;
	}
}
