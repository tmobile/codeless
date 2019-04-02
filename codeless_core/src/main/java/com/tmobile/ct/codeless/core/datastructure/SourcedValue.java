/*******************************************************************************
 * * Copyright 2018 T Mobile, Inc. or its affiliates. All Rights Reserved.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 *  * use this file except in compliance with the License.  You may obtain a copy
 *  * of the License at
 *  *
 *  *   http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 *  * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 *  * License for the specific language governing permissions and limitations under
 *  * the License.
 ******************************************************************************/
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
