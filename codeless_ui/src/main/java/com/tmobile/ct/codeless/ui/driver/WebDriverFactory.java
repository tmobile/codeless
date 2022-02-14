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
package com.tmobile.ct.codeless.ui.driver;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.common.base.Optional;

/**
 * A factory for creating WebDriver objects.
 *
 * @author Fikreselam Elala
 */
public class WebDriverFactory {

	private static final Logger logger = LoggerFactory.getLogger(WebDriverFactory.class);

	private static final String EMPTY = "";

	private Map<String, String> testConfig;

	private String testName;

	private boolean runLocal;

	private String platformType;

	private WebDriver driver = null;
	
	private final static String userDir = System.getProperty("user.dir");

	private static final String WEBDRIVER_CONFIG = "webdriver.platformName";

	private static final String WEBDRIVER_VERSION = "webdriver.browserVersion";

	/**
	 * Creates a instance of driver factory and configures with relevant data.
	 * 
	 * @param testConfig
	 */
	public WebDriverFactory(Map<String, String> testConfig, String testName) {
		this.testConfig = testConfig;
		this.testName = testName;

		platformType = Optional.fromNullable(testConfig.get(com.tmobile.ct.codeless.core.config.Config.PLATFORM_TYPE))
				.or(EMPTY);
		runLocal = Optional
				.fromNullable(Boolean
						.parseBoolean(testConfig.get(com.tmobile.ct.codeless.core.config.Config.WEBDRIVER_RUN_LOCAL)))
				.or(true);
		logger.info("testName: {}", testName);
		logger.info("runLocal: {}", runLocal); 
		logger.info("platformType: {}", platformType);

		// if not local run
		if (runLocal) {
			for (Entry<String, String> e : testConfig.entrySet()) {
				if (e.getKey().startsWith("webdriver.path") && e.getKey().contains(platformType)) {
					String driverName = "chrome";
					switch (platformType.toLowerCase()) {
					case "firefox":
						driverName = "gecko";
						break;
					case "emulator":
						driverName = "chrome";
						break;
					case "edge":
						driverName = "edge";
						break;
					case "opera":
						driverName = "opera";
						break;
					default:
						driverName = platformType;
						break;
					}
					if (System.getProperty("webdriver." + driverName + ".driver") == null)
						System.setProperty("webdriver." + driverName + ".driver", getWebDriverPath(e.getValue()));
				}
			}
		}
	}

	public WebDriver create() {

		if ("iPad".equalsIgnoreCase(platformType)) {

			driver = createMobileWebDriver(platformType); // create iPad WebDriver
		} else {

			if (runLocal) {
				driver = createLocalDriver(platformType); // creates local driver.
				initWebDriver(driver);
			} else {
				driver = createRemoteDriver(platformType); // creates remote driver.
			}
		}
		return driver;
	}
	
	

	private WebDriver createLocalDriver(String platformType) {

		switch (platformType.toLowerCase()) {
		case "firefox":
			driver = new FirefoxDriver();
			break;
		case "edge":
			driver = new EdgeDriver();
			break;
		case "opera":
			driver = new OperaDriver();
			break;
		case "safari":
			driver = new SafariDriver();
			break;
		default:
			driver = new ChromeDriver();
			break;
		}
		return driver;
	}

	private WebDriver createRemoteDriver(String platformType) {

		String hubOS = Optional.fromNullable(testConfig.get(WEBDRIVER_CONFIG.concat("." + platformType.toLowerCase())))
				.or(EMPTY);
		String browserVersion = Optional
				.fromNullable(testConfig.get(WEBDRIVER_VERSION.concat("." + platformType.toLowerCase()))).or(EMPTY);

		SupportedPlatform platform = SupportedPlatform.findFor(platformType);
		String hub = Optional.fromNullable(testConfig.get("webdriver.hub")).or(EMPTY);

		String parentTunnel = Optional.fromNullable(testConfig.get("webdriver.parentTunnel")).or(EMPTY);
		String tunnelIdentifier = (String) Optional.fromNullable(testConfig.get("webdriver.tunnelIdentifier"))
				.or(EMPTY);

		String sboxToken = Optional.fromNullable(testConfig.get("webdriver.e34:token")).or(EMPTY);
		String sboxVideo = Optional.fromNullable(testConfig.get("webdriver.e34:video")).or(EMPTY);
		String testTimeout = Optional.fromNullable(testConfig.get("webdriver.e34:per_test_timeout_ms")).or(EMPTY);

		Map<String, String> additionalProperties = new HashMap<String, String>();
		additionalProperties.put("platformName", hubOS);
		additionalProperties.put("browserVersion", browserVersion);
		additionalProperties.put("parentTunnel", parentTunnel);
		additionalProperties.put("tunnelIdentifier", tunnelIdentifier);

		// adding support for sbox execution.
		additionalProperties.put("e34:token", sboxToken);
		additionalProperties.put("e34:video", sboxVideo);
		additionalProperties.put("e34:l_testName", testName);
		additionalProperties.put("e34:per_test_timeout_ms", testTimeout);
		while (additionalProperties.values().remove(EMPTY));

		return getWebDriver(platform, hub, additionalProperties);
	}

	private WebDriver createMobileWebDriver(String platformType) {

		String deviceName = Optional
				.fromNullable(testConfig.get("webdriver.deviceName".concat("." + platformType.toLowerCase())))
				.or(EMPTY);
		String platformVersion = Optional
				.fromNullable(testConfig.get("webdriver.platformVersion".concat("." + platformType.toLowerCase())))
				.or(EMPTY);
		String platformName = Optional
				.fromNullable(testConfig.get("webdriver.platformName".concat("." + platformType.toLowerCase())))
				.or(EMPTY);
		String bundleId = Optional
				.fromNullable(testConfig.get("webdriver.bundleId".concat("." + platformType.toLowerCase()))).or(EMPTY);
		String udid = Optional.fromNullable(testConfig.get("webdriver.udid".concat("." + platformType.toLowerCase())))
				.or(EMPTY);

		SupportedPlatform platform = SupportedPlatform.findFor(platformType);
		String hub = Optional.fromNullable(testConfig.get("webdriver.hub")).or(EMPTY);
		Map<String, String> additionalProperties = new HashMap<String, String>();
		additionalProperties.put("platformName", platformName);
		additionalProperties.put("platformVersion", platformVersion);
		additionalProperties.put("deviceName", deviceName);
		additionalProperties.put("bundleId", bundleId);
		additionalProperties.put("udid", udid);
		additionalProperties.put("clearSystemFiles", "true");
		additionalProperties.put("automationName", "XCUITest");

		return getWebDriver(platform, hub, additionalProperties);
	}

	private String getWebDriverPath(String webDriverPath) {
		webDriverPath = Paths.get(userDir + File.separator + webDriverPath).toString();
		logger.debug("webDriverPath {}",webDriverPath);
		return webDriverPath;
	}

	private void initWebDriver(WebDriver driver) {
		try {
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
			driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void teardown(WebDriver driver) {
		if (driver != null) {
			driver.quit();
		}
		driver = null;
	}

	public static String getScreenhot(WebDriver driver, String string) throws Exception {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		// after execution, you could see a folder "TestsScreenshots" folder
		String destination = userDir + File.separator + "TestsScreenshots" + File.separator + string + dateName + ".png";
		File finalDestination = new File(Paths.get(destination).toString());
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}

	private WebDriver getWebDriver(SupportedPlatform platform, String hub, Map<String, String> additionalProperties) {

		DesiredCapabilities desiredCap = platform.createCapabilities()
				.merge(new DesiredCapabilities(additionalProperties));
		try {
			WebDriver driver = platform.getDriverClass().getConstructor(URL.class, Capabilities.class)
					.newInstance(new URL(hub), desiredCap);
			return driver;
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
	}
}