package com.tmobile.ct.codeless.service.httpclient;

import java.util.List;

import com.tmobile.ct.codeless.core.datastructure.MultiValue;

/**
 * The Class PathParam.
 *
 * @author Rob Graff
 */
public class PathParam extends MultiValue<String,String> {

	/**
	 * Instantiates a new path param.
	 *
	 * @param key the key
	 * @param value the value
	 */
	public PathParam(String key, String value) {
		super(key, value);
	}
	
	/**
	 * Instantiates a new path param.
	 *
	 * @param key the key
	 * @param values the values
	 */
	public PathParam(String key, List<String> values){
		super(key, values);
	}
	
	/**
	 * Instantiates a new path param.
	 */
	public PathParam(){}

}