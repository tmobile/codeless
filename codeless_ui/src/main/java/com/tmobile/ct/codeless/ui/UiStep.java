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

import java.util.List;
import java.util.concurrent.Future;

import org.openqa.selenium.WebDriver;

import com.tmobile.ct.codeless.core.Retryable;
import com.tmobile.ct.codeless.core.Step;
import com.tmobile.ct.codeless.core.Trackable;
import com.tmobile.ct.codeless.testdata.RequestModifier;
import com.tmobile.ct.codeless.testdata.TestDataInput;
import com.tmobile.ct.codeless.ui.action.UiAction;
import com.tmobile.ct.codeless.ui.assertion.UiAssertionBuilder;
import com.tmobile.ct.codeless.ui.testdata.UiStepExportBuilder;

/**
 * The Interface UiStep.
 *
 * @author Rob Graff
 */
public interface UiStep extends Step, Trackable, Retryable {

	/**
	 * Gets the web driver.
	 *
	 * @return the web driver
	 */
	Future<WebDriver> getWebDriver();

	/**
	 * Sets the action.
	 *
	 * @param action the new action
	 */
	void setAction(UiAction action);

	List<TestDataInput> getTestDataInputs();

	List<RequestModifier> getRequestModifiers();

	void setAssertionBuilder(List<UiAssertionBuilder> assertionBuilder);

	List<UiAssertionBuilder> getAssertionBuilder();
	
	void setUiStepExportBuilder(List<UiStepExportBuilder> uiStepExportBuilder);
	
	List<UiStepExportBuilder> getUiStepExportBuilder();
	
}
