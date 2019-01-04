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
