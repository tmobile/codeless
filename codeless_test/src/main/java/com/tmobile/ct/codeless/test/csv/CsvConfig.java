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
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.google.common.base.Optional;
import com.tmobile.ct.codeless.configuration.CodelessConfiguration;

/**
 * The Class CsvConfig.
 *
 * @author Sai Chandra Korpu
 */
public class CsvConfig {

	public static Map<String, String> getCSVProperties() {
		
		Properties properties = CodelessConfiguration.getProperties();
		Enumeration<Object> keys = properties.keys();
		Map<String, String> config = new HashMap<>();

		while (keys.hasMoreElements()) {
			
			String key = keys.nextElement().toString();
			String value = Optional.fromNullable(properties.getProperty(key)).or(com.tmobile.ct.codeless.core.config.Config.EMPTY);
			config.put(key, value);
		}

		return config;
	}
}
