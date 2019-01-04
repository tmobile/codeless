package com.tmobile.ct.codeless.core;

import java.util.List;
import java.util.Map;

import com.tmobile.ct.codeless.core.datastructure.MultiValue;
import com.tmobile.ct.codeless.core.datastructure.MultiValueMap;

/**
 * The Interface StepInputRow.
 *
 * @author Rob Graff
 */
public interface StepInputRow {

	/**
	 * Gets the values.
	 *
	 * @return the values
	 */
	MultiValueMap<String,MultiValue<String,String>> getValues();
	
	/**
	 * Gets the.
	 *
	 * @param key the key
	 * @return the list
	 */
	List<String> get(String key);
}
