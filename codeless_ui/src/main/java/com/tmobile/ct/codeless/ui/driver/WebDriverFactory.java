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
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.google.common.base.Optional;
import com.tmobile.ct.codeless.core.Config;

/**
 * A factory for creating WebDriver objects.
 *
 * @author Fikreselam Elala
 */
public class WebDriverFactory {

	/** The test config. */
	private static Config testConfig;

	/** The Constant EMPTY. */
	private static final String EMPTY = "";

	/** The Constant WEBDRIVER_CONFIG. */
	private static final String WEBDRIVER_CONFIG = "webdriver.platform";

	/** The Constant WEBDRIVER_VERSION. */
	private static final String WEBDRIVER_VERSION = "webdriver.version";

	/** The driver. */
	private static WebDriver driver = null;

	/** The platform type. */
	private static String platformType = null;

	/**
	 * Sets the config.
	 *
	 * @param config the new config
	 */
	public static void setConfig(Config config) {
		// setup properties for driver creation
		testConfig = config;
	}

	/**
	 * Teardown.
	 */
	public static void teardown() {
		if (driver != null)
		{
			driver.quit();
		}
		driver = null;
	}

	/**
	 * Creates a new WebDriver object.
	 *
	 * @author Rob Graff
	 * @return the web driver
	 */
	private static WebDriver createLocalDriver() {
		String webDriver = "";
		String webDriverPath = "";
		String path = System.getProperty("user.dir");
		platformType = Optional.fromNullable(testConfig.get("platform-type").fullfill()).or(EMPTY);

		if ("iPad".equalsIgnoreCase(platformType)) {
			createMobileWebDriver(platformType); //create iPad WebDriver
		} else {

			String runLocal = Optional.fromNullable(testConfig.get("webdriver.runlocal").fullfill()).or(EMPTY);
			if ("false".equalsIgnoreCase(runLocal)) {
				return createRemoteDriver(platformType);
			}
			switch (platformType.toLowerCase()) {
				case "firefox": {
					webDriver = "webdriver.gecko.driver";
					if (testConfig.get("webdriver.path.firefox") == null)	return null;
					webDriverPath = getWebDriverPath(testConfig.get("webdriver.path.firefox").fullfill());
					System.setProperty(webDriver, webDriverPath);
					driver = new FirefoxDriver();
					break;
				}
				case "microsoftedge": {
					webDriver = "webdriver.ie.driver";
					if (testConfig.get("webdriver.path.ie") == null)	return null;
					webDriverPath = getWebDriverPath(testConfig.get("webdriver.path.ie").fullfill());
					System.setProperty(webDriver, webDriverPath);
					driver = new InternetExplorerDriver();
					break;
				}
				default: {
					webDriver = "webdriver.chrome.driver";
					if (testConfig.get("webdriver.path.chrome") == null)	return null;
					webDriverPath = getWebDriverPath(testConfig.get("webdriver.path.chrome").fullfill());
					System.setProperty(webDriver, webDriverPath);
					driver = new ChromeDriver();
					break;
				}
			}
		}
		initWebDriver();
		return driver;
	}
	
	private static String getWebDriverPath(String webDriverPath) {

		String opertatingSystem = System.getProperty("os.name").toLowerCase();
		String path = System.getProperty("user.dir");
		if (opertatingSystem.contains("windows")) {

			webDriverPath = path + "\\" + webDriverPath.replace("/", "\\");
			return webDriverPath;

		}
		return  path + "//" + webDriverPath;
	}

	/**
	 * Gets the web driver.
	 *
	 * @return the web driver
	 */
	public static WebDriver getWebDriver() {
		if(driver != null)
			return driver;
		return createLocalDriver();
	}

	/**
	 * Creates a new WebDriver object.
	 *
	 * @author Rob Graff
	 * @param plateformType the plateform type
	 * @return the web driver
	 */
	private static WebDriver createRemoteDriver(String plateformType) {
		String hubOS = Optional.fromNullable(testConfig.get(WEBDRIVER_CONFIG.concat("."+plateformType.toLowerCase())).fullfill()).or(EMPTY);
		String plateformVersion = Optional.fromNullable(testConfig.get(WEBDRIVER_VERSION.concat("."+plateformType.toLowerCase())).fullfill()).or(EMPTY);
		SupportedPlatform platform = SupportedPlatform.findFor(platformType);
		String hub = Optional.fromNullable(testConfig.get("webdriver.hub").fullfill()).or(EMPTY);
		String parentTunnel = Optional.fromNullable(testConfig.get("webdriver.parentTunnel").fullfill()).or(EMPTY);
		String tunnelIdentifier = Optional.fromNullable(testConfig.get("webdriver.tunnelIdentifier").fullfill()).or(EMPTY);
		Map<String, String> additionalProperties = new HashMap<String, String>();
		additionalProperties.put("platform", hubOS);
		additionalProperties.put("version", plateformVersion);
		additionalProperties.put("parentTunnel",parentTunnel);
		additionalProperties.put("tunnelIdentifier", tunnelIdentifier);

		return getWebDriver(platform, hub, additionalProperties);
	}

	/**
	 * Creates a new WebDriver object for iPad connected with Appium Server.
	 *
	 * @author Albert Lin
	 * @param plateformType the plateform type
	 * @return the web driver for iPad
	 */
	private static WebDriver createMobileWebDriver(String platformType) {
		String deviceName = Optional.fromNullable(testConfig.get("webdriver.deviceName".concat("." + platformType.toLowerCase())).fullfill()).or(EMPTY);
		String platformVersion = Optional.fromNullable(testConfig.get("webdriver.platformVersion".concat("." + platformType.toLowerCase())).fullfill()).or(EMPTY);
		String platformName = Optional.fromNullable(testConfig.get("webdriver.platformName".concat("." + platformType.toLowerCase())).fullfill()).or(EMPTY);
		String bundleId = Optional.fromNullable(testConfig.get("webdriver.bundleId".concat("." + platformType.toLowerCase())).fullfill()).or(EMPTY);
		String udid = Optional.fromNullable(testConfig.get("webdriver.udid".concat("." + platformType.toLowerCase())).fullfill()).or(EMPTY);

		SupportedPlatform platform = SupportedPlatform.findFor(platformType);
		String hub = Optional.fromNullable(testConfig.get("webdriver.hub").fullfill()).or(EMPTY);
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

	private static WebDriver getWebDriver(SupportedPlatform platform, String hub, Map<String, String> additionalProperties) {
		DesiredCapabilities desiredCap = platform.createCapabilities().merge(new DesiredCapabilities(additionalProperties));
		try {
			driver = platform.getDriverClass().getConstructor(URL.class, Capabilities.class).newInstance(new URL(hub), desiredCap);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | MalformedURLException e) {
			e.printStackTrace();
		}
		return driver;
	}

	/**
	 * Inits the web driver.
	 */
	public static void initWebDriver() {
		try {
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets the screenhot.
	 *
	 * @param driver the driver
	 * @param string the string
	 * @return the screenhot
	 * @throws Exception the exception
	 */
	public static String getScreenhot(WebDriver driver, String string) throws Exception {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		// after execution, you could see a folder "FailedTestsScreenshots" folder
		String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/" + string + dateName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}
}
