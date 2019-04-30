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
package com.tmobile.selenium.sam.action.driver;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tmobile.ct.codeless.core.UiAction;
import com.tmobile.ct.codeless.core.UiActionLog;
import com.tmobile.selenium.sam.action.actions.Action;
import com.tmobile.selenium.sam.action.log.UiActionLogger;
import com.tmobile.selenium.sam.action.report.StepResult;
import com.tmobile.selenium.sam.action.types.ClickType;
import com.tmobile.selenium.sam.action.types.NavigateType;
import com.tmobile.selenium.sam.action.types.TestStatus;
import com.tmobile.selenium.sam.action.utils.Element;
import com.tmobile.selenium.sam.report.TestResult;
import com.tmobile.selenium.sam.report.TestResultManager;

/**
 * The Class ActionDriver.
 *
 * @author Rob Graff
 */
public class ActionDriver {
	
	/** The log. */
	private static Logger log = LoggerFactory.getLogger(ActionDriver.class);
	
	/** The test result. */
	private TestResult testResult = TestResultManager.getTestResult();
	
	private static int duration;
	
	/**
	 * Instantiates a new action driver.
	 */
	public ActionDriver(){
		
	}

	/**
	 * Run.
	 *
	 * @param action the action
	 */
	public void run(Action action) {
		try{
			action.execute();
			StepResult result = action.getStepResult();
			duration = (int) result.getTime();
			log.info("action[" + action.type() + "] status[" + result.getStepStatus().name() + "] time["
					+ result.getTime() + "] " + action.toString() + " screenshot[" + result.getScreenshotLocation()
					+ "]");
			createUiActionLog(action);
			testResult.addStepResult(result);
			if (!result.getStepBool() && !action.isOptional()) {
				log.info(result.getExceptionString());
				testResult.setStatus(TestStatus.fail);
				testResult.end();
				throw result.getException();
			}else if(!result.getStepBool() && action.isOptional()){
				log.info("optional action failed with exception[ "+result.getExceptionString()+" ]");
			}
		}catch(Throwable ex){
			FailedActionException fae = new FailedActionException();
			fae.initCause(ex);
			throw fae;
		}
	}
	
	private static void createUiActionLog(Action action) {
		Element element = null;
		switch (action.type()) {
		case Click:
			element = (Element) getFieldByClassType(action, Element.class, "element");
			UiActionLogger.add(new UiActionLog(UiAction.CLICK, element.locator().toString(), duration));
			ClickType clickType = (ClickType) getFieldByClassType(action, ClickType.class, "clickType");
			if (clickType == ClickType.javascript) {
				UiActionLogger.add(new UiActionLog(UiAction.SCRIPT, "arguments[0].click();", null));
			}
			break;
		case Send:
			element = (Element) getFieldByClassType(action, Element.class, "element");
			UiActionLogger.add(new UiActionLog(UiAction.SEND_TEXT, element.locator(), duration));
			break;
		case Go:
			String url = (String) getFieldByClassType(action, String.class, "url");
			UiActionLogger.add(new UiActionLog(UiAction.GET_URL, url, duration));
			break;
		case Read:
			String text = (String) getFieldByClassType(action, String.class, "url");
			UiActionLogger.add(new UiActionLog(UiAction.GET_TEXT, text, duration));
			break;
		case Navigate:
			NavigateType navigateType = (NavigateType) getFieldByClassType(action, NavigateType.class, "navType");
			UiActionLogger.add(new UiActionLog(UiAction.NAVIGATE, navigateType.toString(), duration));
			break;
		}
	}
	
	private static Object getFieldByClassType(Action action, Class classType, String fieldName) {

		for (Field field : action.getClass().getDeclaredFields()) {
			if (classType == field.getType() && field.getName().equalsIgnoreCase(fieldName)) {
				if (Modifier.isPrivate(field.getModifiers()))
					field.setAccessible(true);
				try {
					return field.get(action);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
