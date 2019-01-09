package com.tmobile.ct.codeless.testdata;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.tmobile.ct.codeless.core.TestData;

public class TestDataProvider implements TestDataReference<String>{

	private static String OVERRIDE_INPUT = "{{";

	private String key;

	private TestData testData;


	public TestDataProvider(TestData testData, String key) {
		this.key = key;
		this.testData = testData;
	}

	@Override
	public String find() {

		// Add check for environment also
		//String prefix = value.substring(0, 5);

		if(testData!= null) {
			// check test data in system properties first
			String sys_value = System.getProperty(key);
			if(!StringUtils.isEmpty(sys_value)) {
				return sys_value;
			}

			// check test data in system environments
			String sysEnv = System.getenv(key);
			if(!StringUtils.isEmpty(sysEnv)) {
				return sysEnv;
			}
			Map<String, String> data = testData.asMap();
			// check test data for override value
			if(data.containsKey(key)) {
				String overrideValue = data.get(key);
				if(!StringUtils.isEmpty(overrideValue.trim())) {
					return overrideValue;
				}
			}
		}
		return key;
	}


}
