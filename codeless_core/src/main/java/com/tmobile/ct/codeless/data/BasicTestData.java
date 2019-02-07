package com.tmobile.ct.codeless.data;

import java.util.HashMap;
import java.util.Map;

import com.tmobile.ct.codeless.core.TestData;
import com.tmobile.ct.codeless.core.TestDataSource;

/**
 * The Class BasicTestData.
 *
 * @author Rob Graff
 */
public class BasicTestData extends BasicSourcedDataMap implements TestData{

	@Override
	public Map<String, TestDataSource> asMap() {
		HashMap<String,TestDataSource> map = new HashMap<>();
		super.keySet().forEach(key ->{
			map.put(key, super.get(key));
		});
		return map;
	}

	@Override
	public String getValue(String key) {
		return super.get(key).fullfill();
	}

}
