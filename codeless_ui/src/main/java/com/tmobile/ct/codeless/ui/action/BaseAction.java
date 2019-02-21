package com.tmobile.ct.codeless.ui.action;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.tmobile.ct.codeless.ui.driver.WebDriverNotAvailableException;
import com.tmobile.selenium.sam.config.ActionConfig;

/**
 * The Class BaseAction.
 *
 * @author Rob Graff
 */
public class BaseAction {

	/** The driver. */
	protected Future<WebDriver> driver;

	/** The config. */
	protected ActionConfig config;

	/** The element. */
	protected WebElement element;

	/** The failure exception. */
	protected Throwable failureException;

	/**
	 * Instantiates a new base action.
	 *
	 * @param driver the driver
	 * @param config the config
	 * @param element the element
	 */
	public BaseAction(Future<WebDriver> driver, ActionConfig config, WebElement element) {
		this.driver = driver;
		this.config = config;
		this.element = element;
	}

	/**
	 * Gets the driver.
	 *
	 * @return the driver
	 */
	protected WebDriver getDriver(){
		try {
			return driver.get(60, TimeUnit.SECONDS);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			WebDriverNotAvailableException ex = new WebDriverNotAvailableException("Timed out after 30 seconds waiting for WebDriver to be available");
			ex.initCause(e);
			throw ex;
		}
	}

	/**
	 * Fail.
	 *
	 * @param e the e
	 */
	public void fail(Throwable e) {
		this.failureException = e;

	}

	/**
	 * Gets the failure cause.
	 *
	 * @return the failure cause
	 */
	public Throwable getFailureCause() {
		return failureException;
	}

	public WebElement getElement() {
		return element;
	}

	public void setElement(WebElement element) {
		this.element = element;
	}

}
