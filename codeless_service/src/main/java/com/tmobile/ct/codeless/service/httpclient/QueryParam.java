package com.tmobile.ct.codeless.service.httpclient;

import java.util.List;

import com.tmobile.ct.codeless.core.datastructure.MultiValue;

/**
 * The Class QueryParam.
 *
 * @author Rob Graff
 */
public class QueryParam extends MultiValue<String,String> {

	/**
	 * Instantiates a new query param.
	 *
	 * @param key the key
	 * @param value the value
	 */
	public QueryParam(String key, String value) {
		super(key, value);
	}
	
	/**
	 * Instantiates a new query param.
	 *
	 * @param key the key
	 * @param values the values
	 */
	public QueryParam(String key, List<String> values){
		super(key, values);
	}
	
	/**
	 * Instantiates a new query param.
	 */
	public QueryParam(){}

}
