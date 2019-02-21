package com.tmobile.ct.codeless.service.accessor.request;

import com.tmobile.ct.codeless.service.HttpRequest;
import com.tmobile.ct.codeless.service.accessor.response.ResponseAccessor;
import com.tmobile.ct.codeless.service.httpclient.Cookie;

/**
 * The Class CookieModifier.
 *
 * @author Rob Graff
 */
public class CookieModifier implements RequestModifier<Cookie>{

	/** The key. */
	private String key;
	
	/** The response accessor. */
	private ResponseAccessor responseAccessor;
	
	/**
	 * Instantiates a new cookie modifier.
	 *
	 * @param key the key
	 * @param responseAccessor the response accessor
	 */
	public CookieModifier(String key, ResponseAccessor responseAccessor){
		this.key = key;
		this.responseAccessor = responseAccessor;
	}
	
	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.accessor.request.RequestModifier#modify(com.tmobile.ct.codeless.service.HttpRequest)
	 */
	@Override
	public void modify(HttpRequest request) {
		Cookie newCookie = new Cookie(key, responseAccessor.getActual());
		request.getCookies().put(key, newCookie);
		
	}
	
	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.accessor.request.RequestModifier#setResponseAccessor(com.tmobile.ct.codeless.service.accessor.response.ResponseAccessor)
	 */
	@Override
	public void setResponseAccessor(ResponseAccessor responseAccessor) {
		this.responseAccessor = responseAccessor;
	}

}
