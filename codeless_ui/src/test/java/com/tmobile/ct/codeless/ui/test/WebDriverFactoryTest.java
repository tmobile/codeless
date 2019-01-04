package com.tmobile.ct.codeless.ui.test;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.openqa.selenium.WebDriver;

import com.tmobile.ct.codeless.core.Config;
import com.tmobile.ct.codeless.core.datastructure.SourcedValue;
import com.tmobile.ct.codeless.data.BasicConfig;
import com.tmobile.ct.codeless.data.SourcedDataItem;
import com.tmobile.ct.codeless.ui.driver.WebDriverFactory;

public class WebDriverFactoryTest {

	/*@Test
	public void LocalwebDriverTest() {
		Config config = mockLocalRunConfig();
		WebDriverFactory.setConfig(config);
		WebDriver driver = WebDriverFactory.getWebDriver();
		assertNotNull(driver);
		WebDriverFactory.teardown();
	}*/

	@Test
	public void RemotewebDriverTest() {
		Config config = mockRemoteRunConfig();
		WebDriverFactory.setConfig(config);
		WebDriver driver = WebDriverFactory.getWebDriver();
		assertNotNull(driver);
		WebDriverFactory.teardown();
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
			SourcedValue<String> value = new SourcedValue<>();
			value.setValue(keyValue);

			SourcedDataItem<String,String> item = new SourcedDataItem<>(key, value);
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
		excelConfig.put("webdriver.hub", "https://tmo_get_started:112088af-4aa4-4371-9515-802f05fecb23@ondemand.saucelabs.com/wd/hub");


		for(Map.Entry<String, String> entry : excelConfig.entrySet()) {
			String key = entry.getKey();
			String keyValue = entry.getValue();
			SourcedValue<String> value = new SourcedValue<>();
			value.setValue(keyValue);

			SourcedDataItem<String,String> item = new SourcedDataItem<>(key, value);
			config.put(key, item);
		}
		return config;
	}

}
