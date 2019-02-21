package com.tmobile.ct.codeless.core;

import java.util.Map;

/**
 * The Interface TestData.
 *
 * @author Rob Graff
 */
public interface TestData extends SourcedData{

	/**
	 * As map.
	 *
	 * @return the map
	 */
	Map<String, TestDataSource> asMap();

	String getValue(String key);

}
