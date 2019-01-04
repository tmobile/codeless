package com.tmobile.ct.codeless.core.datastructure;

/**
 * The Class SourcedValue.
 *
 * @author Rob Graff
 * @param <V> the value type
 */
public class SourcedValue<V> {

	/** The source. */
	private String source;
	
	/** The source class. */
	private Class<?> sourceClass;
 	
	 /** The value. */
	 private V value;
 	
 	/**
	  * Instantiates a new sourced value.
	  */
	 public SourcedValue(){}
 	
	/**
	 * Gets the source.
	 *
	 * @return the source
	 */
	public String getSource() {
		return source;
	}
	
	/**
	 * Sets the source.
	 *
	 * @param source the source
	 * @return the sourced value
	 */
	public SourcedValue<V> setSource(String source) {
		this.source = source;
		return this;
	}
	
	/**
	 * Gets the source class.
	 *
	 * @return the source class
	 */
	public Class<?> getSourceClass() {
		return sourceClass;
	}
	
	/**
	 * Sets the source class.
	 *
	 * @param sourceClass the source class
	 * @return the sourced value
	 */
	public SourcedValue<V> setSourceClass(Class<?> sourceClass) {
		this.sourceClass = sourceClass;
		return this;
	}
	
	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public V getValue() {
		return value;
	}
	
	/**
	 * Sets the value.
	 *
	 * @param value the value
	 * @return the sourced value
	 */
	public SourcedValue<V> setValue(V value) {
		this.value = value;
		return this;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SourcedValue [source=").append(source).append(", sourceClass=").append(sourceClass)
				.append(", value=").append(value).append("]");
		return builder.toString();
	} 
}
