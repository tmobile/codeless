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

import com.tmobile.ct.codeless.ui.driver.WebDriverFactory;

public class WebDriverFactoryTest {
	
	private WebDriverFactory webDriverFactory;
	
	private Map<String, String> config;

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
		webDriverFactory = new WebDriverFactory(config,"test");
	}

	@Test
	public void RemotewebDriverTest() {
		WebDriver driver = webDriverFactory.create();
		assertNotNull(driver);
		WebDriverFactory.teardown(driver);
	}

	public Map<String, String> mockLocalRunConfig() {
		Map<String, String> excelConfig = new HashMap<String, String>();
		excelConfig.put("platform-type", "chrome");
		excelConfig.put("webdriver.runlocal", "TRUE");
		excelConfig.put("webdriver.path.chrome", "web_drivers/windows/chromedriver.exe");
		excelConfig.put("webdriver.path.firefox", "web_drivers/windows/geckodriver.exe");
		excelConfig.put("webdriver.path.edge", "web_drivers/windows/msedgedriver.exe");

		return excelConfig;
	}

	public  Map<String, String> mockRemoteRunConfig() {
		Map<String, String> excelConfig = new HashMap<String, String>();
		excelConfig.put("platform-type", "chrome");
		excelConfig.put("webdriver.runlocal", "FALSE");
		excelConfig.put("webdriver.platformName.chrome", "Windows");
		excelConfig.put("webdriver.browserVersion.chrome", "90.0");
		excelConfig.put("webdriver.hub", "https://127.0.0.1");

		return excelConfig;
	}

}
