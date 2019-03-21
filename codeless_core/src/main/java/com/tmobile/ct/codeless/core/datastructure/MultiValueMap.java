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

import java.util.HashMap;
import java.util.Set;

/**
 * The Class MultiValueMap.
 *
 * @author Rob Graff
 * @param <K> the key type
 * @param <V> the value type
 */
public class MultiValueMap<K,V extends MultiValue> extends HashMap<K, V> {

	
	/**
	 * Adds the.
	 *
	 * @param key the key
	 * @param value the value
	 * @return the v
	 */
	public V add(K key, V value){
		if(super.containsKey(key)){
			V existing = super.get(key);
			existing.getValues().addAll(value.getValues());
			return super.put(key, existing);
		}else{
			return super.put(key, value);
		}
	}
	
	/* (non-Javadoc)
	 * @see java.util.HashMap#put(java.lang.Object, java.lang.Object)
	 */
	@Override
	public V put(K key, V value){
		return super.put(key, value);
	}

	/**
	 * Stream.
	 *
	 * @return the sets the
	 */
	public Set<java.util.Map.Entry<K, V>> stream(){
		return super.entrySet();
	}

}
