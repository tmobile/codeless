package com.tmobile.ct.codeless.service.httpclient;

import java.util.List;

import com.tmobile.ct.codeless.core.datastructure.MultiValue;

/**
 * The Class Header.
 *
 * @author Rob Graff
 */
public class Header extends MultiValue<String, String>{

	/**
	 * Instantiates a new header.
	 *
	 * @param key the key
	 * @param value the value
	 */
	public Header(String key, List<String> value) {
		super(key, value);
	}
	
	/**
	 * Instantiates a new header.
	 *
	 * @param key the key
	 * @param value the value
	 */
	public Header(String key, String value){
		super(key, value);
	}
	
	/**
	 * Instantiates a new header.
	 */
	public Header(){}

}
