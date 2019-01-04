package com.tmobile.ct.codeless.core;

import java.util.Map;
import java.util.Properties;

/**
 * The Interface Config.
 *
 * @author Rob Graff
 */
public interface Config extends SourcedData{

	/**
	 * As properties.
	 *
	 * @return the properties
	 */
	Properties asProperties();

	/**
	 * As map.
	 *
	 * @return the map
	 */
	Map<String, String> asMap();
}
