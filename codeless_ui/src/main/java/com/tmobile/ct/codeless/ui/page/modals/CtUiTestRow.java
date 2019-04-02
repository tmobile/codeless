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

import java.util.List;

import org.openqa.selenium.WebDriver;

import com.tmobile.ct.codeless.core.Assertion;
import com.tmobile.ct.codeless.core.Component;
import com.tmobile.ct.codeless.core.Config;
import com.tmobile.ct.codeless.core.Result;
import com.tmobile.ct.codeless.core.Status;
import com.tmobile.ct.codeless.core.Step;
import com.tmobile.ct.codeless.core.Test;
import com.tmobile.ct.codeless.core.Trackable;
import com.tmobile.ct.codeless.ui.assertion.UiAssertionBuilder;
import com.tmobile.ct.codeless.ui.build.UiTestStep;
import com.tmobile.ct.codeless.ui.driver.WebDriverFactory;
import com.tmobile.ct.codeless.ui.model.ControlElement;

/**
 * The Class CtUiTestRow.
 *
 * @author Rob Graff
 */
public class CtUiTestRow implements Step, Trackable {

	/** The name. */
	private String name;
	
	/** The step. */
	private UiTestStep step;
	
	/** The control element. */
	private ControlElement controlElement;
	
	/** The assertion builder. */
	private List<UiAssertionBuilder> assertionBuilder;

	/** The test. */
	private Test test;

	/**
	 * Gets the assertion builder.
	 *
	 * @return the assertion builder
	 */
	public List<UiAssertionBuilder> getAssertionBuilder() {
		return assertionBuilder;
	}

	/**
	 * Sets the assertion builder.
	 *
	 * @param assertionBuilder the new assertion builder
	 */
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

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Validatable#getAssertions()
	 */
	@Override
	public List<Assertion> getAssertions() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Validatable#setAssertions(java.util.List)
	 */
	@Override
	public void setAssertions(List<Assertion> assertions) {
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Executable#run()
	 */
	@Override
	public void run() {
		try {
			Config config = test.getConfig();
		    WebDriverFactory.setConfig(config);
			WebDriver driver = WebDriverFactory.getWebDriver();
			test.setWebDriver(driver);

			CtSeleniumPageActions pageObjects = new CtSeleniumPageActions(test.getWebDriver());
			pageObjects.setTest(getTest());
			pageObjects.callAction(this);
		} catch (Exception e) {
			fail(e);
			RuntimeException re = new RuntimeException("UI Step Failure");
			re.initCause(e);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Failable#fail(java.lang.Throwable)
	 */
	@Override
	public void fail(Throwable e) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Failable#getFailureCause()
	 */
	@Override
	public Throwable getFailureCause() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Validatable#validate()
	 */
	@Override
	public void validate() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Step#getName()
	 */
	@Override
	public String getName() {
		return this.name;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Step#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
	this.name = name;

	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Step#getTest()
	 */
	@Override
	public Test getTest() {
		return test;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Step#setTest(com.tmobile.ct.codeless.core.Test)
	 */
	@Override
	public void setTest(Test test) {
		this.test = test;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Trackable#getResult()
	 */
	@Override
	public Result getResult() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Trackable#getStatus()
	 */
	@Override
	public Status getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Trackable#setResult(com.tmobile.ct.codeless.core.Result)
	 */
	@Override
	public void setResult(Result result) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Trackable#setStatus(com.tmobile.ct.codeless.core.Status)
	 */
	@Override
	public void setStatus(Status status) {
		// TODO Auto-generated method stub

	}

	@Override
	public Component getComponent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setComponent(Component component) {
		// TODO Auto-generated method stub
		
	}


}