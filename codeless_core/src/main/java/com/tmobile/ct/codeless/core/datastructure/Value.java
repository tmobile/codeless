package com.tmobile.ct.codeless.core.datastructure;

/**
 * The Class Value.
 *
 * @author Rob Graff
 * @param <V> the value type
 */
public class Value<V> {

	/** The value. */
	private V value;
	
	/**
	 * Instantiates a new value.
	 *
	 * @param value the value
	 */
	public Value(V value){
		this.value = value;
	}
	
	/**
	 * Instantiates a new value.
	 */
	public Value(){}
	
	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public V getValue(){
		return value;
	}
	
	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(V value){
		this.value = value;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Value [value=").append(value).append("]");
		return builder.toString();
	}
}
