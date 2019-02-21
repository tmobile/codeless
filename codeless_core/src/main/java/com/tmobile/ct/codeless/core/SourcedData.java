package com.tmobile.ct.codeless.core;

import java.util.Map;
import java.util.Optional;

import com.tmobile.ct.codeless.data.SourcedDataItem;

/**
 * The Interface SourcedData.
 *
 * @author Rob Graff
 */
public interface SourcedData {

	/**
	 * Sets the config.
	 *
	 * @param config the config
	 */
	void setConfig(Map<String,SourcedDataItem<String,TestDataSource>> config);

	/**
	 * Put.
	 *
	 * @param key the key
	 * @param item the item
	 * @return the sourced data item
	 */
	SourcedDataItem<String,TestDataSource> put(String key, SourcedDataItem<String,TestDataSource> item);

	/**
	 * Gets the sourced value.
	 *
	 * @param key the key
	 */
	SourcedDataItem<String,TestDataSource> getSourcedValue(String key);

	/**
	 * Gets the.
	 *
	 * @param key the key
	 * @return the string
	 */
	TestDataSource get(String key);

	/**
	 * Gets the config.
	 *
	 * @return the config
	 */
	Map<String,SourcedDataItem<String,TestDataSource>> getConfig();


	Optional<String> getOptional(String key);
}
