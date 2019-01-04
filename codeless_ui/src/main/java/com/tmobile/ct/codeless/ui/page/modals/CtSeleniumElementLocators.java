package com.tmobile.ct.codeless.ui.page.modals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * The Class CtSeleniumElementLocators.
 *
 * @author Rob Graff
 */
public class CtSeleniumElementLocators {
	
	/** The driver. */
	private WebDriver driver;

	/**
	 * Instantiates a new ct selenium element locators.
	 *
	 * @param webDriver the web driver
	 */
	public CtSeleniumElementLocators(WebDriver webDriver) {
		this.driver = webDriver;
	}

	/**
	 * Find by element.
	 *
	 * @param locator the locator
	 * @param locatorValue the locator value
	 * @return the web element
	 * @throws Exception the exception
	 */
	public WebElement findByElement(String locator, String locatorValue) throws Exception {
		CtSeleniumUiLocators loc = CtSeleniumUiLocators.valueOf(locator.toLowerCase());
		switch (loc) {
		case id:
			return this.driver.findElement(By.id(locatorValue));
		case css:
			return this.driver.findElement(By.cssSelector(locatorValue));
		case xpath:
			return this.driver.findElement(By.xpath(locatorValue));
		case tagname:
			return this.driver.findElement(By.tagName(locatorValue));
		case linktext:
			return this.driver.findElement(By.linkText(locatorValue));
		case partiallinktext:
			return this.driver.findElement(By.partialLinkText(locatorValue));
		case name:
			return this.driver.findElement(By.name(locatorValue));
		case classname:
			return this.driver.findElement(By.className(locatorValue));
		default:
			throw new Exception("Element does not exists on the webpage by " + loc + " with value " + locatorValue);
		}
	}
	
	/**
	 * Gets the driver.
	 *
	 * @return the driver
	 */
	protected WebDriver getDriver(){
		return this.driver;
	}
}
