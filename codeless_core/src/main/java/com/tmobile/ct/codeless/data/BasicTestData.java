package com.tmobile.ct.codeless.data;

import java.util.HashMap;
import java.util.Map;

import com.tmobile.ct.codeless.core.TestData;

/**
 * The Class BasicTestData.
 *
 * @author Rob Graff
 */
public class BasicTestData extends BasicSourcedDataMap implements TestData{

	@Override
	public Map<String, String> asMap() {
		HashMap<String,String> map = new HashMap<>();
		super.keySet().forEach(key ->{
			map.put(key, super.get(key));
		});
		return map;
	}

}
