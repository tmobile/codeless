package com.tmobile.ct.codeless.service.accessor.request;

import com.tmobile.ct.codeless.core.TestDataSource;
import com.tmobile.ct.codeless.service.HttpRequest;
import com.tmobile.ct.codeless.service.httpclient.Cookie;
import com.tmobile.ct.codeless.testdata.RequestModifier;

/**
 * The Class CookieModifier.
 *
 * @author Rob Graff
 */
public class CookieModifier implements RequestModifier<Cookie, HttpRequest>{

	/** The key. */
	private String key;

	/** The dataSource to override. */
	private TestDataSource dataSource;

	/**
	 * Instantiates a new cookie modifier.
	 *
	 * @param key the key
	 * @param responseAccessor the response accessor
	 */
	public CookieModifier(String key, TestDataSource dataSource){
		this.key = key;
		this.dataSource = dataSource;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.service.accessor.request.RequestModifier#modify(com.tmobile.ct.codeless.service.HttpRequest)
	 */
	@Override
	public void modify(HttpRequest request) {
		Cookie newCookie = new Cookie(key, dataSource.fullfill());
		request.getCookies().put(key, newCookie);

	}

}
