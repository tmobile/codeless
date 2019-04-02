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
import java.util.List;
import java.util.Map;

/**
 * The Class BasicParamMap.
 *
 * @author Rob Graff
 * @param <V> the value type
 */
public class BasicParamMap<V extends MultiValue<String,String>> extends MultiValueMap<String, V>{


	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.datastructure.MultiValueMap#put(java.lang.Object, com.tmobile.ct.codeless.core.datastructure.MultiValue)
	 */
	@Override
	public V put(String key, V value){
		return super.put(key.toLowerCase(), value);
	}
	
	
	/**
	 * To values map.
	 *
	 * @return the map
	 */
	public Map<String, List<String>> toValuesMap(){
		Map<String,List<String>> valuesMap = new HashMap<>();
		super.entrySet().stream().forEach(x -> valuesMap.put(x.getKey(), x.getValue().getValues()));
		return valuesMap;
	}
}
