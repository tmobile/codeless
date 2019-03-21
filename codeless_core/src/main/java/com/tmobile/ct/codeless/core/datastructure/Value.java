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
