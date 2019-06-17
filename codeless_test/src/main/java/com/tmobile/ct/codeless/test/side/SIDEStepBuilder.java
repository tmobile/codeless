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
package com.tmobile.ct.codeless.test.side;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tmobile.ct.codeless.core.Test;
import com.tmobile.ct.codeless.test.side.data.SIDEStep;
import com.tmobile.ct.codeless.ui.UiStep;
import com.tmobile.ct.codeless.ui.UiStepImpl;
import com.tmobile.ct.codeless.ui.action.UiAction;
import com.tmobile.ct.codeless.ui.assertion.SeleniumMethodType;
import com.tmobile.ct.codeless.ui.assertion.UiAssertionBuilder;
import com.tmobile.ct.codeless.ui.assertion.UiAssertionMethod;
import com.tmobile.ct.codeless.ui.assertion.UiSeleniumMethod;
import com.tmobile.ct.codeless.ui.build.UiStepBuilder;
import com.tmobile.ct.codeless.ui.build.UiTestStep;
import com.tmobile.selenium.sam.config.ActionConfig;

/**
 * The Class SIDEStepBuilder.
 *
 * @author Sai Chandra Korpu
 */
public class SIDEStepBuilder {

	private static Logger logger = LoggerFactory.getLogger(SIDEStepBuilder.class);

	Test test;

	private List<String> testData = new ArrayList<>();

	public UiStep build(Test test, SIDEStep sideStep) {

		this.test = test;

		UiStep step = new UiStepImpl();
		UiTestStep uiTestStep = buildUiTestStep(test, sideStep, step);
		if (uiTestStep != null) {
			step.setName(uiTestStep.getStep());
			UiStepBuilder uiStepBuilder = new UiStepBuilder();
			ActionConfig config = uiStepBuilder.buildConfig(testData);
			config.message = step.getName();

			UiAction action = uiStepBuilder.buildAction(uiTestStep, config, step);
			step.setAction(action);

			return step;
		}
		return null;
	}

	private UiTestStep buildUiTestStep(Test test, SIDEStep sideStep, UiStep uiStep) {

		UiTestStep step = new UiTestStep();
		String uiActionType = getActionType(sideStep.getCommand());
		if (StringUtils.isNotBlank(uiActionType)) {
			step.setAction(uiActionType);
			step.setStep(sideStep.getComment());
			if (uiActionType.equalsIgnoreCase("goto")) {
				step.setInput(sideStep.getTarget());
			} else {
				step.setInput(sideStep.getValue());
			}
			step.setTarget("side::" + sideStep.getTarget());
			setTestData(sideStep);
			if (sideStep.getCommand().contains("assert")) {
				uiStep.setAssertionBuilder(getUiAssertions(sideStep));
			}
			return step;
		}
		return null;
	}

	private List<UiAssertionBuilder> getUiAssertions(SIDEStep sideStep) {

		List<UiAssertionBuilder> assertions = new ArrayList<>();
		Method seleniumMethod = null;
		Method assertionMethod = null;
		SeleniumMethodType type = null;
		String seleniumMethodName = "";
		String numFormat = "";
		String parameter = "";
		String expected = "";

		if (sideStep.getCommand().contains("assertTitle")) {
			type = SeleniumMethodType.WebDriver;
			seleniumMethodName = "getTitle";
			expected = sideStep.getTarget();
		} else if (sideStep.getCommand().contains("assertText")) {
			type = SeleniumMethodType.WebElement;
			seleniumMethodName = "getText";
			expected = sideStep.getValue();
		}
		try {
			seleniumMethod = UiSeleniumMethod.getSeleniumMethod(seleniumMethodName, parameter, type);
			assertionMethod = UiAssertionMethod.getAssertionMethod("assertEquals", expected);
			UiAssertionBuilder assertion = new UiAssertionBuilder(assertionMethod, expected, seleniumMethod, type,
					parameter, numFormat);
			assertions.add(assertion);
			return assertions;
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			return null;
		}
	}

	public String getActionType(String action) {
		switch (action.trim().toUpperCase()) {
		case "OPEN":
			return "GOTO";
		case "TYPE":
			return "SENDKEYS";
		case "CLOSE":
			return "CLOSE";
		case "CLICK":
			return "CLICK";
		case "WAITFORELEMENTVISIBLE":
			return "WAIT";
		case "ASSERTTITLE":
			return "WAIT";
		case "ASSERTTEXT":
			return "WAIT";
		default:
			return null;
		}
	}

	private void setTestData(SIDEStep sideStep) {
		switch (sideStep.getCommand().trim().toUpperCase()) {
		case "ASSERTTITLE":
			testData.add("waitType::waitForPageLoad");
			break;
		case "ASSERTTEXT":
			testData.add("waitType::waitForPageLoad");
			break;
		case "WAITFORELEMENTVISIBLE":
			testData.add("waitType::visible");
			setWaitTime(sideStep);
			break;
		}
	}

	private void setWaitTime(SIDEStep sideStep) {
		if (sideStep.getValue() != null) {
			testData.add("waitTime::" + sideStep.getValue());
		}
	}

}
