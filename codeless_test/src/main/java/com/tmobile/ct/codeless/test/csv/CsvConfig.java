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
package com.tmobile.ct.codeless.test.csv;

import java.util.Enumeration;
import java.util.Properties;

import com.tmobile.ct.codeless.configuration.CodelessConfiguration;
import com.tmobile.ct.codeless.core.Config;
import com.tmobile.ct.codeless.core.TestDataSource;
import com.tmobile.ct.codeless.core.datastructure.SourcedValue;
import com.tmobile.ct.codeless.data.BasicConfig;
import com.tmobile.ct.codeless.data.SourcedDataItem;
import com.tmobile.ct.codeless.testdata.StaticTestDataSource;

/**
 * The Class CsvConfig.
 *
 * @author Sai Chandra Korpu
 */
public class CsvConfig {

	public static Config getCSVProperties() {
		
		Properties properties = CodelessConfiguration.getProperties();
		Enumeration<Object> keys = properties.keys();
		Config config = new BasicConfig();

		while (keys.hasMoreElements()) {
			
			String key = keys.nextElement().toString();
			String value = properties.getProperty(key);
			
			StaticTestDataSource staticSource = new StaticTestDataSource(key, value);
			SourcedValue<TestDataSource> dS = new SourcedValue<>();
			dS.setSource(CsvConfig.class.getName());
			dS.setSourceClass(CsvConfig.class);
			dS.setValue(staticSource);
			SourcedDataItem<String, TestDataSource> item = new SourcedDataItem<>(key, dS);
			config.put(key, item);
		}
		return config;

	}
}
