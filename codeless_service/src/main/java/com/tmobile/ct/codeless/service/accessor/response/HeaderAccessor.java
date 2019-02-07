package com.tmobile.ct.codeless.service.accessor.response;

import java.util.concurrent.ExecutionException;

import com.tmobile.ct.codeless.core.Accessor;
import com.tmobile.ct.codeless.service.assertion.ServiceAssertionActualProvider;
import com.tmobile.ct.codeless.service.core.ServiceCall;
import com.tmobile.ct.codeless.service.httpclient.Header;
import com.tmobile.ct.codeless.service.reference.ServiceCallReference;

/**
 * The Class HeaderAccessor.
 *
 * @author Rob Graff
 */
public class HeaderAccessor implements Accessor<Header, ServiceCall>, ServiceAssertionActualProvider<String> {

	/** The key. */
	private String key;

	/** The call. */
	private ServiceCall call;

	/** The call ref. */
	private ServiceCallReference callRef;

	/**
	 * Instantiates a new header accessor.
	 *
	 * @param callRef the call ref
	 * @param key the key
	 */
	public HeaderAccessor(ServiceCallReference callRef,  String key){
		this.callRef = callRef;
		this.key = key;
	}

	/**
	 * Instantiates a new header accessor.
	 *
	 * @param key the key
	 */
	public HeaderAccessor(String key){
		this.key = key;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.assertion.ServiceAssertionActualProvider#getActual(com.tmobile.ct.codeless.service.core.ServiceCall)
	 */
	@Override
	public String getActual(ServiceCall call) {
		return call.getHttpResponse().getHeaders().get(key).getValues().get(0);
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.assertion.ServiceAssertionActualProvider#getName()
	 */
	@Override
	public String getName() {
		return "Header: "+key;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.accessor.response.ResponseAccessor#read()
	 */
	@Override
	public Header read() {
		return this.call.getHttpResponse().getHeaders().get(key);
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

	@Override
	public String value() {
		return key;
	}

}