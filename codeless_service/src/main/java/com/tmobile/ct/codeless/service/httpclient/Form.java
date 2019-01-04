package com.tmobile.ct.codeless.service.httpclient;

import java.util.List;

import com.tmobile.ct.codeless.core.datastructure.MultiValue;

/**
 * The Class Form.
 *
 * @author Rob Graff
 */
public class Form extends MultiValue<String,String>{

	/**
	 * Instantiates a new form.
	 *
	 * @param key the key
	 * @param value the value
	 */
	public Form(String key, List<String> value) {
		super(key, value);
	}
	
	/**
	 * Instantiates a new form.
	 *
	 * @param key the key
	 * @param value the value
	 */
	public Form(String key, String value) {
		super(key, value);
	}
	
	/**
	 * Instantiates a new form.
	 */
	public Form(){}

}
