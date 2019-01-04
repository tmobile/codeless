package com.tmobile.ct.codeless.service.accessor.request;

import com.tmobile.ct.codeless.service.HttpRequest;
import com.tmobile.ct.codeless.service.accessor.response.ResponseAccessor;
import com.tmobile.ct.codeless.service.httpclient.ServicePath;

/**
 * The Class ServicePathModifier.
 *
 * @author Rob Graff
 */
public class ServicePathModifier implements RequestModifier<ServicePath>{

	/** The response accessor. */
	private ResponseAccessor responseAccessor;
	
	/**
	 * Instantiates a new service path modifier.
	 *
	 * @param responseAccessor the response accessor
	 */
	public ServicePathModifier(ResponseAccessor responseAccessor){
		this.responseAccessor = responseAccessor;
	}
	
	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.accessor.request.RequestModifier#modify(com.tmobile.ct.codeless.service.HttpRequest)
	 */
	@Override
	public void modify(HttpRequest request) {
		request.setServicePath(new ServicePath(responseAccessor.getActual()));
		
	}
	
	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.accessor.request.RequestModifier#setResponseAccessor(com.tmobile.ct.codeless.service.accessor.response.ResponseAccessor)
	 */
	@Override
	public void setResponseAccessor(ResponseAccessor responseAccessor) {
		this.responseAccessor = responseAccessor;
	}
}
