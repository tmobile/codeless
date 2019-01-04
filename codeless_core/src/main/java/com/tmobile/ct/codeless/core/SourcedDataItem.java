package com.tmobile.ct.codeless.core;

/**
 * The Interface SourcedDataItem.
 *
 * @author Rob Graff
 */
public interface SourcedDataItem {

	/**
	 * Sets the key.
	 */
	void setKey();
	
	/**
	 * Sets the value.
	 */
	void setValue();
	
	/**
	 * Sets the source.
	 */
	void setSource();
	
	/**
	 * Gets the key.
	 *
	 * @return the key
	 */
	String getKey();
	
	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	String getValue();
	
	/**
	 * Gets the source.
	 *
	 * @return the source
	 */
	String getSource();
}
