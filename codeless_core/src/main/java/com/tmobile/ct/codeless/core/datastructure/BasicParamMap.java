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
