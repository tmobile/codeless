package com.tmobile.ct.codeless.service.accessor.request;

import com.tmobile.ct.codeless.service.HttpRequest;
import com.tmobile.ct.codeless.service.accessor.response.ResponseAccessor;
import com.tmobile.ct.codeless.service.httpclient.Header;
import com.tmobile.ct.codeless.service.reference.ServiceCallReference;

/**
 * The Class HeaderModifier.
 *
 * @author Rob Graff
 */
public class HeaderModifier implements RequestModifier<Header>{

	/** The key. */
	private String key;
	
	/** The response accessor. */
	private ResponseAccessor responseAccessor;
	
	/**
	 * Instantiates a new header modifier.
	 *
	 * @param key the key
	 * @param responseAccessor the response accessor
	 */
	public HeaderModifier(String key, ResponseAccessor responseAccessor){
		this.key = key;
		this.responseAccessor = responseAccessor;
	}
	
	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.accessor.request.RequestModifier#modify(com.tmobile.ct.codeless.service.HttpRequest)
	 */
	@Override
	public void modify(HttpRequest request) {
		Header newHeader = new Header(key, responseAccessor.getActual());
		request.getHeaders().put(newHeader.getKey(), newHeader);
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.accessor.request.RequestModifier#setResponseAccessor(com.tmobile.ct.codeless.service.accessor.response.ResponseAccessor)
	 */
	@Override
	public void setResponseAccessor(ResponseAccessor responseAccessor) {
		this.responseAccessor = responseAccessor;
	}
}
