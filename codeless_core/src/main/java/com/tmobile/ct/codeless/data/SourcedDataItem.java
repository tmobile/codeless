package com.tmobile.ct.codeless.data;

import com.tmobile.ct.codeless.core.datastructure.SourcedValue;

/**
 * The Class SourcedDataItem.
 *
 * @author Rob Graff
 * @param <K> the key type
 * @param <V> the value type
 */
public class SourcedDataItem<K,V> {

	/** The key. */
	private K key;
	
	/** The value. */
	private SourcedValue<V> value;
	
	/**
	 * Instantiates a new sourced data item.
	 */
	public SourcedDataItem(){
		
	}
	
	/**
	 * Instantiates a new sourced data item.
	 *
	 * @param key the key
	 * @param value the value
	 */
	public SourcedDataItem(K key, SourcedValue<V> value){
		this.key = key;
		this.value = value;
	}

	/**
	 * Gets the key.
	 *
	 * @return the key
	 */
	public K getKey() {
		return key;
	}

	/**
	 * Sets the key.
	 *
	 * @param key the key
	 * @return the sourced data item
	 */
	public SourcedDataItem<K,V> setKey(K key) {
		this.key = key;
		return this;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public SourcedValue<V> getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 *
	 * @param value the value
	 * @return the sourced data item
	 */
	public SourcedDataItem<K,V> setValue(SourcedValue<V> value) {
		this.value = value;
		return this;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SourcedConfigItem [key=").append(key).append(", value=").append(value).append("]");
		return builder.toString();
	}
}
