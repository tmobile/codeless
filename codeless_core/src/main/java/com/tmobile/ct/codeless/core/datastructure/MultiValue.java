package com.tmobile.ct.codeless.core.datastructure;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class MultiValue.
 *
 * @author Rob Graff
 * @param <K> the key type
 * @param <V> the value type
 */
public class MultiValue<K,V> {

	/** The key. */
	K key;
	
	/** The values. */
	List<V> values;
	
	/**
	 * Instantiates a new multi value.
	 *
	 * @param key the key
	 * @param value the value
	 */
	public MultiValue(K key, V value){
		this.key = key;
		this.values = new ArrayList<>();
		this.values.add(value);
	}
	
	/**
	 * Instantiates a new multi value.
	 *
	 * @param key the key
	 * @param value the value
	 */
	public MultiValue(K key, List<V> value){
		this.key = key;
		this.values = value;
	}
	
	/**
	 * Instantiates a new multi value.
	 */
	public MultiValue(){}

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
	 * @param key the new key
	 */
	public void setKey(K key) {
		this.key = key;
	}

	/**
	 * Gets the values.
	 *
	 * @return the values
	 */
	public List<V> getValues() {
		return values;
	}

	/**
	 * Sets the values.
	 *
	 * @param values the new values
	 */
	public void setValues(List<V> values) {
		this.values = values;
	}
	
	/**
	 * Adds the value.
	 *
	 * @param value the value
	 * @return the multi value
	 */
	public MultiValue<K, V> addValue(V value){
		this.values.add(value);
		return this;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MultiValue [key=").append(key).append(", values=").append(values).append("]");
		return builder.toString();
	}
	
	
}
