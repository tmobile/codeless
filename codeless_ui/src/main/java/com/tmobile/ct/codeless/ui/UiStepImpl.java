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
package com.tmobile.ct.codeless.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tmobile.ct.codeless.core.Assertion;
import com.tmobile.ct.codeless.core.Component;
import com.tmobile.ct.codeless.core.Result;
import com.tmobile.ct.codeless.core.Status;
import com.tmobile.ct.codeless.core.Test;
import com.tmobile.ct.codeless.testdata.RequestModifier;
import com.tmobile.ct.codeless.testdata.TestDataInput;
import com.tmobile.ct.codeless.ui.action.UiAction;
import com.tmobile.ct.codeless.ui.assertion.UiAssertion;
import com.tmobile.ct.codeless.ui.assertion.UiAssertionBuilder;
import com.tmobile.ct.codeless.ui.build.UiTestStep;
import com.tmobile.ct.codeless.ui.driver.WebDriverFactory;
import com.tmobile.ct.codeless.ui.model.ControlElement;
import com.tmobile.ct.codeless.ui.modifiers.AssertionModifer;
import com.tmobile.ct.codeless.ui.testdata.UiStepExportBuilder;
import com.tmobile.ct.codeless.ui.testdata.UiStepExportVariable;

/**
 * The Class UiStepImpl.
 *
 * @author Rob Graff
 */
public class UiStepImpl implements UiStep {

	/** The name. */
	private String name;

	/** The step. */
	private UiTestStep step;

	/** The control element. */
	private ControlElement controlElement;

	/** The assertion builder. */
	private List<UiAssertionBuilder> assertionBuilder;

	/** The request modifiers. */
	private List<RequestModifier> requestModifiers = new ArrayList<>();

	/** The test. */
	private Test test;

	/** The action. */
	private UiAction action;

	/** The failure cause. */
	private Throwable failureCause;

	/** The status. */
	private Status status;

	/** The result. */
	private Result result;

	/** The retries. */
	private Integer retries = 0;

	/** The max retries. */
	private Integer maxRetries = 0;

	/** The driver. */
	private CompletableFuture<WebDriver> driver = new CompletableFuture<>();

	private List<TestDataInput> testDataInputs;

	private Component component;

	private List<UiStepExportBuilder> uiStepExportBuilder;

	private String description;

	private String screenShotPath;

	private Integer order;
	
	private static final Logger logger = LoggerFactory.getLogger(UiStepImpl.class);

	/**
	 * Instantiates a new ui step impl.
	 */
	public UiStepImpl(){
		this.testDataInputs = new ArrayList<TestDataInput>();
	}

	/**
	 * Instantiates a new ui step impl.
	 *
	 * @param action the action
	 */
	public UiStepImpl(UiAction action){
		this.action = action;
	}

	@Override
	public void run() {

		try {
			buildRequestModifier();		
			
			if(test.getWebDriver() == null) {
				WebDriverFactory webDriverFactory = new WebDriverFactory(test.getConfig(), test.getName());
				WebDriver driver = webDriverFactory.create();
				test.setWebDriver(driver);
				logger.info("driver info {}",driver.toString());
			}
			setWebDriver(test.getWebDriver());
			this.action.run();

			if (uiStepExportBuilder != null && !uiStepExportBuilder.isEmpty()) {
				UiStepExportVariable.buildExport(test, getUiStepExportBuilder(), action.getElement());
			}
			if (assertionBuilder != null && !assertionBuilder.isEmpty()) {
				UiAssertion.buildAssertions(test, getAssertionBuilder(), action.getElement());
			}

			status = Status.COMPLETE;
			if(result == null)
				result = Result.PASS;
		} catch(Exception e){
			retries = retries + 1;
			if(retries >= maxRetries){
				status = Status.COMPLETE;
				result = Result.FAIL;
				fail(e);
				logFail(e);
			}
		}finally{
			markComplete();
		}
	}

	private void buildRequestModifier() {
		for(RequestModifier modifier : requestModifiers) {
			if(modifier != null)
				if(modifier instanceof AssertionModifer) {
					modifier.modify(assertionBuilder.get(0),test);
				}else {

					modifier.modify(action,test);
				}
		}
	}

	/**
	 * Mark complete.
	 */
	private void markComplete() {
		this.status = Status.COMPLETE;
	}

	/**
	 * Log fail.
	 *
	 * @param e the e
	 */
	private void logFail(Exception e) {
		e.printStackTrace();
	}

	/**
	 * Gets the assertion builder.
	 *
	 * @return the assertion builder
	 */
	@Override
	public List<UiAssertionBuilder> getAssertionBuilder() {
		return assertionBuilder;
	}

	/**
	 * Sets the assertion builder.
	 *
	 * @param assertionBuilder the new assertion builder
	 */
	@Override
	public void setAssertionBuilder(List<UiAssertionBuilder> assertionBuilder) {
		this.assertionBuilder = assertionBuilder;
	}

	/**
	 * Gets the control element.
	 *
	 * @return the control element
	 */
	public ControlElement getControlElement() {
		return controlElement;
	}

	/**
	 * Sets the control element.
	 *
	 * @param controlElement the new control element
	 */
	public void setControlElement(ControlElement controlElement) {
		this.controlElement = controlElement;
	}

	/**
	 * Gets the step.
	 *
	 * @return the step
	 */
	public UiTestStep getStep() {
		return step;
	}

	/**
	 * Sets the step.
	 *
	 * @param step the new step
	 */
	public void setStep(UiTestStep step) {
		this.step = step;
	}

	@Override
	public List<Assertion> getAssertions() {
		return null;
	}

	@Override
	public void setAssertions(List<Assertion> assertions) {
	}

	@Override
	public void fail(Throwable e) {
		this.failureCause = e;
	}

	@Override
	public Throwable getFailureCause() {
		return failureCause;
	}

	@Override
	public void validate() {
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
	this.name = name;
	}

	@Override
	public Test getTest() {
		return test;
	}

	@Override
	public void setTest(Test test) {
		this.test = test;
	}

	@Override
	public Result getResult() {
		return result;
	}

	@Override
	public Status getStatus() {
		return status;
	}

	@Override
	public void setResult(Result result) {
		this.result = result;
	}

	@Override
	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public void setMaxRetries(Integer retries) {
		this.maxRetries = retries;
	}

	@Override
	public Integer getMaxRetries() {
		return maxRetries;
	}

	@Override
	public Integer getRetries() {
		return retries;
	}

	/**
	 * Gets the action.
	 *
	 * @return the action
	 */
	@Override
	public UiAction getAction() {
		return action;
	}

	@Override
	public void setAction(UiAction action) {
		this.action = action;
	}

	@Override
	public Future<WebDriver> getWebDriver(){
		return driver;
	}

	/**
	 * Sets the web driver.
	 *
	 * @param driver the new web driver
	 */
	public void setWebDriver(WebDriver driver){
		this.driver.complete(driver);
	}

	@Override
	public List<TestDataInput> getTestDataInputs() {
		return testDataInputs;
	}

	public void setTestDataInputs(List<TestDataInput> testDataInputs) {
		this.testDataInputs = testDataInputs;
	}

	@Override
	public List<RequestModifier> getRequestModifiers() {
		return requestModifiers;
	}

	public void setRequestModifiers(List<RequestModifier> requestModifiers) {
		this.requestModifiers = requestModifiers;
	}

	@Override
	public Component getComponent() {
		return component;
	}

	@Override
	public void setComponent(Component component) {
		this.component = component;
	}

	@Override
	public void setUiStepExportBuilder(List<UiStepExportBuilder> uiStepExportBuilder) {
		this.uiStepExportBuilder = uiStepExportBuilder;

	}

	@Override
	public List<UiStepExportBuilder> getUiStepExportBuilder() {
		return this.uiStepExportBuilder;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String getScreenShotPath() {
		return screenShotPath;
	}

	@Override
	public void setScreenShotPath(String screenShotPath) {
		this.screenShotPath = screenShotPath;
	}

	@Override
	public Integer getOrder() {
		return order;
	}

	@Override
	public void setOrder(Integer order) {
		this.order = order;
	}
}
