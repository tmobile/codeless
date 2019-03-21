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
