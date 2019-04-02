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


import static org.junit.Assert.assertNotNull;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import com.tmobile.ct.codeless.configuration.CodelessConfiguration;
import com.tmobile.ct.codeless.core.Config;
import com.tmobile.ct.codeless.testdata.StaticTestDataSource;


public class CsvConfigTest {
	
	@Before
	public void setUp() {
		
		setCsvProperties();
	}

	@Test
	public void getPropertiesTest() {
		
		Config properties = CsvConfig.getCSVProperties();
		assertNotNull(properties);
		StaticTestDataSource ds = (StaticTestDataSource) properties.get("webdriver.runlocal");
		assertNotNull(ds);
		assertEquals(ds.getValue(), "TRUE");
		
	}
	
	public static void setCsvProperties() {
		
		Properties props = new Properties();
		props.setProperty("model.dir", File.separator + "model");
		props.setProperty("suites.dir", File.separator + "suites");
		props.setProperty("webdriver.runlocal", "TRUE");
		CodelessConfiguration.setProperties(props);
	}
}
