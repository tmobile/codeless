package com.tmobile.ct.codeless.service.accessor.request;

import com.tmobile.ct.codeless.service.HttpRequest;
import com.tmobile.ct.codeless.service.accessor.response.ResponseAccessor;

/**
 * The Class BodyTemplateModifier.
 *
 * @author Rob Graff
 */
public class BodyTemplateModifier implements RequestModifier<String>{

	/** The key. */
	private String key;
	
	/** The response accessor. */
	private ResponseAccessor responseAccessor;
	
	/**
	 * Instantiates a new body template modifier.
	 *
	 * @param key the key
	 * @param responseAccessor the response accessor
	 */
	public BodyTemplateModifier(String key, ResponseAccessor responseAccessor){
		this.key = key;
		this.responseAccessor = responseAccessor;
	}
	
	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.accessor.request.RequestModifier#modify(com.tmobile.ct.codeless.service.HttpRequest)
	 */
	@Override
	public void modify(HttpRequest request) {
		String value = responseAccessor.getActual();
		request.getBody().setBody(request.getBody().asString().replace(getTemplateKey(key), value));
	}
	
	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.accessor.request.RequestModifier#setResponseAccessor(com.tmobile.ct.codeless.service.accessor.response.ResponseAccessor)
	 */
	@Override
	public void setResponseAccessor(ResponseAccessor responseAccessor) {
		this.responseAccessor = responseAccessor;
	}
	
	/**
	 * Gets the template key.
	 *
	 * @param key the key
	 * @return the template key
	 */
	private String getTemplateKey(String key){
		return "{{"+key+"}}";
	}

}
