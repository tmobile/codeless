package com.tmobile.ct.codeless.service.accessor.response;

import java.util.concurrent.ExecutionException;

import com.tmobile.ct.codeless.core.Accessor;
import com.tmobile.ct.codeless.service.assertion.ServiceAssertionActualProvider;
import com.tmobile.ct.codeless.service.core.ServiceCall;
import com.tmobile.ct.codeless.service.reference.ServiceCallReference;

/**
 * The Class BodyStringAccessor.
 *
 * @author Rob Graff
 */
public class BodyStringAccessor implements Accessor<String, ServiceCall>, ServiceAssertionActualProvider<String>{

	/** The call ref. */
	private ServiceCallReference callRef;

	/** The call. */
	private ServiceCall call;

	/**
	 * Instantiates a new body string accessor.
	 *
	 * @param callRef the call ref
	 */
	public BodyStringAccessor(ServiceCallReference callRef){
		this.callRef = callRef;
	}

	/**
	 * Instantiates a new body string accessor.
	 */
	public BodyStringAccessor(){}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.assertion.ServiceAssertionActualProvider#getActual(com.tmobile.ct.codeless.service.core.ServiceCall)
	 */
	@Override
	public String getActual(ServiceCall call) {
		return call.getHttpResponse().getBody().asString();
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.assertion.ServiceAssertionActualProvider#getName()
	 */
	@Override
	public String getName() {
		return "Full Response Body";
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.accessor.response.ResponseAccessor#read()
	 */
	@Override
	public String read() {
		return this.call.getHttpResponse().getBody().asString();
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.accessor.response.ResponseAccessor#setServiceCall(com.tmobile.ct.codeless.service.core.ServiceCall)
	 */
	@Override
	public void setServiceCall(ServiceCall call) {
		this.call = call;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.accessor.response.ResponseAccessor#getActual()
	 */
	@Override
	public String getActual() {
		ServiceCall call = this.callRef.find();

		try {
			call.isComplete().get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}

		return getActual(call);
	}
}
