package com.tmobile.ct.codeless.testdata;

import java.util.Map;

/**
 * The Class TestDataProvider.
 *
 * @author Fikreselam Elala
 */

import org.apache.commons.lang3.StringUtils;

import com.tmobile.ct.codeless.core.Test;
import com.tmobile.ct.codeless.core.TestData;
import com.tmobile.ct.codeless.core.TestDataSource;

public class TestDataProvider implements TestDataReference<String>{

	private static String OVERRIDE_INPUT = "{{";

	private String key;

	private Test test;

	private TestData td;


	public TestDataProvider(Test test, String key) {
		this.key = key;
		this.test = test;
	}

	public TestDataProvider(TestData td , String key) {
		this.td = td;
		this.key = key;
	}

	@Override
	public String find() {

		// Add check for environment also
		//String prefix = value.substring(0, 5);

		if(test != null && test.getTestData() != null) {
			TestData testData = test.getTestData();
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
			Map<String, TestDataSource> data = testData.asMap();
			// check test data for override value
			if(data.containsKey(key)) {
				String overrideValue = data.get(key).fullfill();
				if(!StringUtils.isEmpty(overrideValue.trim())) {
					return overrideValue;
				}
			}else {
				if(test.getConfig() != null && test.getConfig().asMap().containsKey(key)) {
					String configValue = test.getConfig().get(key).fullfill();
					return configValue;
				}
			}
		}else if(td != null) {
			Map<String, TestDataSource> data = td.asMap();
			return data.get(key).fullfill();
		}
		return key;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}




}
