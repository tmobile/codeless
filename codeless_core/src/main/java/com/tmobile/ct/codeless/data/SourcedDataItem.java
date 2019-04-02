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
