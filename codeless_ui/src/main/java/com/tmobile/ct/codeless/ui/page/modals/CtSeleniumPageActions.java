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
package com.tmobile.ct.codeless.ui.page.modals;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.tmobile.ct.codeless.core.Test;
import com.tmobile.ct.codeless.ui.assertion.SeleniumMethodType;
import com.tmobile.ct.codeless.ui.assertion.UiAssertionBuilder;

/**
 * The Class CtSeleniumPageActions.
 *
 * @author Rob Graff
 */
public class CtSeleniumPageActions extends CtSeleniumElementLocators {

	/**
	 * Instantiates a new ct selenium page actions.
	 *
	 * @param webDriver the web driver
	 */
	public CtSeleniumPageActions(WebDriver webDriver) {
		super(webDriver);
	}

	/** The js. */
	JavascriptExecutor js = (JavascriptExecutor) this.getDriver();

	/** The test. */
	private Test test;

	/**
	 * The Enum StepStatus.
	 *
	 * @author Rob Graff
	 */
	private enum StepStatus{

		/** The pass. */
		PASS,
/** The fail. */
FAIL,
/** The info. */
INFO
	}

	/**
	 * Log step.
	 *
	 * @param status the status
	 * @param name the name
	 * @param message the message
	 */
	private void logStep(StepStatus status, String name, String message){
		test.getLogProxies().forEach(logger ->{
			logger.log(status.name(), name, message);
		});
	}

	/**
	 * Call action.
	 *
	 * @param step the step
	 * @return the ct selenium page actions
	 * @throws Exception the exception
	 */
	public CtSeleniumPageActions callAction(CtUiTestRow step) throws Exception {
		CtSeleniumUiActions action = CtSeleniumUiActions.valueOf(step.getStep().getAction().toLowerCase());
		String input = step.getStep().getInput();
		String locator, locatorValue;
		List<UiAssertionBuilder> assertions = step.getAssertionBuilder();
		switch (action) {
		case navigate:
			navigate(input, assertions);
			break;
		case sendkeys:
			locator = step.getControlElement().getBy();
			locatorValue = step.getControlElement().getLocator();
			sendKeys(locator, locatorValue, input, assertions);
			break;
		case click:
			locator = step.getControlElement().getBy();
			locatorValue = step.getControlElement().getLocator();
			click(locator, locatorValue, assertions);
			break;
		case close:
			close();
			break;
		default:
			throw new RuntimeException("action does not exists" + action);
		}
		return this;
	}

	/**
	 * Navigate.
	 *
	 * @param input the input
	 * @param assertions the assertions
	 * @throws Exception the exception
	 */
	private void navigate(String input, List<UiAssertionBuilder> assertions) throws Exception {
		try {
			this.getDriver().get(input);
			Thread.sleep(1000);
			logStep(StepStatus.PASS, "navigate", "Succesful navigation to the page [ " + input + " ] ");
			if ((assertions != null) && !assertions.isEmpty()) {
				buildAssertions(assertions, null);
			}
		} catch (Exception e) {
			logStep(StepStatus.FAIL, "navigate", "Failed to navigate to the page [ " + input + " ] ");
			throw e;
		}
	}

	/**
	 * Send keys.
	 *
	 * @param locator the locator
	 * @param locatorValue the locator value
	 * @param input the input
	 * @param assertions the assertions
	 * @throws Exception the exception
	 */
	private void sendKeys(String locator, String locatorValue, String input, List<UiAssertionBuilder> assertions)
			throws Exception {
		String logInput =  input;
		if( locatorValue != null && locatorValue.toLowerCase().contains("pass")) {
			logInput = "******";
		}
		try {
			WebElement element = findByElement(locator, locatorValue);
			js.executeScript("arguments[0].scrollIntoView(true);", element);
			element.sendKeys(input);
			logStep(StepStatus.PASS, "Send Keys", "Sent text [ " + logInput + " ] to textbox ");
			if ((assertions != null) && !assertions.isEmpty()) {
				buildAssertions(assertions, element);
			}
		} catch (Exception e) {
			logStep(StepStatus.FAIL, "Send Keys", "Failed to send text [ " + logInput + " ] to textbox ");
			throw e;
		}
	}

	/**
	 * Click.
	 *
	 * @param locator the locator
	 * @param locatorValue the locator value
	 * @param assertions the assertions
	 * @throws Exception the exception
	 */
	private void click(String locator, String locatorValue, List<UiAssertionBuilder> assertions) throws Exception {
		try {
			WebElement element = findByElement(locator, locatorValue);
			js.executeScript("arguments[0].scrollIntoView(true);", element);
			element.click();
			logStep(StepStatus.PASS, "Click", " Succesful click on the element by [ " + locator + " ] ");
			Thread.sleep(3000);
			if ((assertions != null) && !assertions.isEmpty()) {
				buildAssertions(assertions, element);
			}
		} catch (Exception e) {
			logStep(StepStatus.FAIL, "Click", " Failed to click on the element by [ " + locator + " ] ");
			throw e;
		}
	}

	/**
	 * Close.
	 *
	 * @throws Exception the exception
	 */
	private void close() throws Exception {
		try {
			this.getDriver().close();
			logStep(StepStatus.PASS, "Click", " Succesful closing the page ");
		} catch (Exception e) {
			logStep(StepStatus.FAIL, "Click", " Failed to close the page");
			throw e;
		}
	}

	/**
	 * Builds the assertions.
	 *
	 * @param assertions the assertions
	 * @param webElement the web element
	 */
	public void buildAssertions(List<UiAssertionBuilder> assertions, WebElement webElement) {
		assertions.forEach(assertion -> {
			Method assertionMethod = assertion.getAssertMethod();
			Method seleniumMethod = assertion.getSeleniumMethod();
			Object ElementorDriver;
			if(assertion.getSeleniumMethodType() == SeleniumMethodType.WebDriver) {
				ElementorDriver = this.getDriver();
			}else {
				ElementorDriver = webElement;
			}
			try {
				if (assertionMethod.getParameterCount() == 1) {
					if(seleniumMethod == null) {
						assertionMethod.invoke(null, ElementorDriver);
						logStep(StepStatus.INFO, "Assertion [ " + assertionMethod + " ] ", "Succesful");
					}
					else if (seleniumMethod.getParameterCount() == 0) {
						assertionMethod.invoke(null, seleniumMethod.invoke(ElementorDriver));
						logStep(StepStatus.INFO, "Assertion [ " + assertionMethod + " ] ", "Succesful on the [ " + seleniumMethod + " ]");
					}
					else if (seleniumMethod.getParameterCount() == 1) {
						assertionMethod.invoke(null, seleniumMethod.invoke(ElementorDriver, assertion.getParameterName()));
						logStep(StepStatus.INFO, "Assertion [ " + assertionMethod + " ] ", "Succesful on the [ " + seleniumMethod + " ] with parameterName ");
					}
				}
				else if (assertionMethod.getParameterCount() == 2) {
					if (seleniumMethod.getParameterCount() == 0) {
						assertionMethod.invoke(null, seleniumMethod.invoke(ElementorDriver), assertion.getExpectedValue());
						logStep(StepStatus.INFO,"Assertion [ " + assertionMethod + " ] ", "Succesful on the [ " + seleniumMethod + " ]");
					}
					else if (seleniumMethod.getParameterCount() == 1) {
						assertionMethod.invoke(null, seleniumMethod.invoke(ElementorDriver, assertion.getParameterName()), assertion.getExpectedValue());
						logStep(StepStatus.INFO, "Assertion [ " + assertionMethod + " ] ", "Succesful on the [ " + seleniumMethod + " ] with parameterName ");
					}
				}

			}
			catch(IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				logStep(StepStatus.FAIL, "Assertion [ " + assertionMethod + " ] ", " Failed on the [ " + seleniumMethod + " ]");
			}
		});
	}

	/**
	 * Sets the test.
	 *
	 * @param test the new test
	 */
	public void setTest(Test test) {
		this.test = test;
	}

}
