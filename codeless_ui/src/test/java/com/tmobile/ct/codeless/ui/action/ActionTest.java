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
package com.tmobile.ct.codeless.ui.action;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.tmobile.ct.codeless.ui.model.ControlElement;
import com.tmobile.ct.codeless.ui.model.controls.WebElementProxyFactory;
import com.tmobile.selenium.sam.action.types.ClickType;
import com.tmobile.selenium.sam.action.types.WaitType;
import com.tmobile.selenium.sam.config.ActionConfig;

public class ActionTest {

	WebDriver driver;
	ActionConfig config;
	CompletableFuture<WebDriver> webDriver = new CompletableFuture<>();

	@Before
	public void setup() {
		ChromeOptions options = new ChromeOptions();  
		options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors");  
		driver = new ChromeDriver(options);  
		driver.get("D:\\Code\\CTA\\edp-workspace\\etp_codeless_ui\\src\\test\\resources\\test_website\\login.html");
		config = new ActionConfig();
		config.clickType = ClickType.click;
		config.waitTime = 2;
		config.waitType = WaitType.enabled;

	}

	@Test
	public void itShouldExecuteAction() {
		try {
			ControlElement control = new ControlElement("id", "username");
			WebElement element = new WebElementProxyFactory().fromControlElement(webDriver, control);
			Click click = new Click(webDriver, config, element);

			webDriver.complete(driver);
			
			click.run();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@After
	public void cleanUp() {
		if (driver != null) {
			driver.quit();
		}
	}
}
