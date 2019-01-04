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
