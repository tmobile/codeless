package com.tmobile.ct.codeless.service.httpclient;

import com.tmobile.ct.codeless.core.datastructure.MultiValue;

/**
 * The Class Cookie.
 *
 * @author Rob Graff
 */
public class Cookie extends MultiValue<String,String>{

	/**
	 * Instantiates a new cookie.
	 *
	 * @param key the key
	 * @param value the value
	 */
	// TODO full cookie implementation
	public Cookie(String key, String value) {
		super(key, value);
	}

	/**
	 * Instantiates a new cookie.
	 */
	public Cookie(){}
}
