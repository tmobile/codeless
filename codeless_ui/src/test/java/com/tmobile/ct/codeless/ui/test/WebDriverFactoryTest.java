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
package com.tmobile.ct.codeless.ui.test;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import com.tmobile.ct.codeless.core.Config;
import com.tmobile.ct.codeless.core.TestDataSource;
import com.tmobile.ct.codeless.core.datastructure.SourcedValue;
import com.tmobile.ct.codeless.data.BasicConfig;
import com.tmobile.ct.codeless.data.SourcedDataItem;
import com.tmobile.ct.codeless.testdata.StaticTestDataSource;
import com.tmobile.ct.codeless.ui.driver.WebDriverFactory;

public class WebDriverFactoryTest {
	
	private WebDriverFactory webDriverFactory;
	
	private Config config;

	/*@Test
	public void LocalwebDriverTest() {
		Config config = mockLocalRunConfig();
		WebDriverFactory.setConfig(config);
		WebDriver driver = WebDriverFactory.getWebDriver();
		assertNotNull(driver);
		WebDriverFactory.teardown();
	}*/
	
	@Before
	public void setUp() {
		config = mockRemoteRunConfig();
		webDriverFactory = new WebDriverFactory(config.asMap(),"test");
	}

	@Test
	public void RemotewebDriverTest() {
		WebDriver driver = webDriverFactory.create();
		assertNotNull(driver);
		WebDriverFactory.teardown(driver);
	}

	public Config mockLocalRunConfig() {
		Config config = new BasicConfig();
		Map<String, String> excelConfig = new HashMap<String, String>();
		excelConfig.put("platform-type", "chrome");
		excelConfig.put("webdriver.runlocal", "TRUE");
		excelConfig.put("webdriver.path.chrome", "web_drivers/windows/chromedriver.exe");
		excelConfig.put("webdriver.path.firefox", "web_drivers/windows/geckodriver.exe");
		excelConfig.put("webdriver.path.ie", "web_drivers/windows/IEDriverServer.exe");

		for(Map.Entry<String, String> entry : excelConfig.entrySet()) {
			String key = entry.getKey();
			String keyValue = entry.getValue();
			StaticTestDataSource staticSource = new StaticTestDataSource(key, keyValue);
			SourcedValue<TestDataSource> value = new SourcedValue<>();
			value.setValue(staticSource);

			SourcedDataItem<String,TestDataSource> item = new SourcedDataItem<>(key, value);
			config.put(key, item);
		}
		return config;
	}

	public Config mockRemoteRunConfig() {
		Config config = new BasicConfig();
		Map<String, String> excelConfig = new HashMap<String, String>();
		excelConfig.put("platform-type", "chrome");
		excelConfig.put("webdriver.runlocal", "FALSE");
		excelConfig.put("webdriver.platform.chrome", "Windows");
		excelConfig.put("webdriver.version.chrome", "61.0");
		excelConfig.put("webdriver.hub", "https://tmo_get_started:a1e75451-66d4-4f7f-afbe-f0d79db0e141@ondemand.saucelabs.com/wd/hub");


		for(Map.Entry<String, String> entry : excelConfig.entrySet()) {
			String key = entry.getKey();
			String keyValue = entry.getValue();
			StaticTestDataSource staticSource = new StaticTestDataSource(keyValue,keyValue);
			SourcedValue<TestDataSource> value = new SourcedValue<>();
			value.setValue(staticSource);

			SourcedDataItem<String,TestDataSource> item = new SourcedDataItem<>(key, value);
			config.put(key, item);
		}
		return config;
	}

}
