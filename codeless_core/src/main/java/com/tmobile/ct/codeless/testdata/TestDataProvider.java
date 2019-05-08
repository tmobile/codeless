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
				String overrideValue = (String) data.get(key).fullfill();
				if(!StringUtils.isEmpty(overrideValue.trim())) {
					return overrideValue;
				}
			}else {
				if(test.getConfig() != null && test.getConfig().asMap().containsKey(key)) {
					String configValue = (String) test.getConfig().get(key).fullfill();
					return configValue;
				}
			}
		}else if(td != null) {
			Map<String, TestDataSource> data = td.asMap();
			return (String) data.get(key).fullfill();
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
