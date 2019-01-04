package com.tmobile.ct.codeless.service.accessor.request;

import com.tmobile.ct.codeless.service.HttpRequest;
import com.tmobile.ct.codeless.service.accessor.response.ResponseAccessor;
import com.tmobile.ct.codeless.service.httpclient.Form;

/**
 * The Class FormModifier.
 *
 * @author Rob Graff
 */
public class FormModifier implements RequestModifier<Form>{

	/** The key. */
	private String key;
	
	/** The response accessor. */
	private ResponseAccessor responseAccessor;
	
	/**
	 * Instantiates a new form modifier.
	 *
	 * @param key the key
	 * @param responseAccessor the response accessor
	 */
	public FormModifier(String key, ResponseAccessor responseAccessor){
		this.key = key;
		this.responseAccessor = responseAccessor;
	}
	
	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.accessor.request.RequestModifier#modify(com.tmobile.ct.codeless.service.HttpRequest)
	 */
	@Override
	public void modify(HttpRequest request) {
		request.getForms().put(key, new Form(key, responseAccessor.getActual()));
		
	}
	
	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.accessor.request.RequestModifier#setResponseAccessor(com.tmobile.ct.codeless.service.accessor.response.ResponseAccessor)
	 */
	@Override
	public void setResponseAccessor(ResponseAccessor responseAccessor) {
		this.responseAccessor = responseAccessor;
	}
}
