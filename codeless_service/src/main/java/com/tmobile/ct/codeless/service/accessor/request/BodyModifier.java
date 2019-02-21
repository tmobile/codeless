package com.tmobile.ct.codeless.service.accessor.request;

import com.tmobile.ct.codeless.service.HttpRequest;
import com.tmobile.ct.codeless.service.accessor.response.ResponseAccessor;
import com.tmobile.ct.codeless.service.httpclient.Body;
import com.tmobile.ct.codeless.service.httpclient.Header;


/**
 * The Class BodyModifier.
 *
 * @author Rob Graff
 */
public class BodyModifier implements RequestModifier<Body>{


	/** The response accessor. */
	private ResponseAccessor responseAccessor;
	
	/**
	 * Instantiates a new body modifier.
	 *
	 * @param responseAccessor the response accessor
	 */
	public BodyModifier( ResponseAccessor responseAccessor){

		this.responseAccessor = responseAccessor;
	}
	
	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.accessor.request.RequestModifier#modify(com.tmobile.ct.codeless.service.HttpRequest)
	 */
	@Override
	public void modify(HttpRequest request) {
		String body = responseAccessor.getActual();
		request.setBody(new Body<String>(body, String.class));
		
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.accessor.request.RequestModifier#setResponseAccessor(com.tmobile.ct.codeless.service.accessor.response.ResponseAccessor)
	 */
	@Override
	public void setResponseAccessor(ResponseAccessor responseAccessor) {
		this.responseAccessor = responseAccessor;
	}

}
