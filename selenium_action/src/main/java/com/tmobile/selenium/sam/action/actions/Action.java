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
package com.tmobile.selenium.sam.action.actions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.tmobile.selenium.sam.action.actions.conditions.Condition;
import com.tmobile.selenium.sam.action.report.StepResult;
import com.tmobile.selenium.sam.action.types.ActionType;
import com.tmobile.selenium.sam.action.types.StepStatus;
import com.tmobile.selenium.sam.action.types.WaitType;
import com.tmobile.selenium.sam.action.utils.ScreenShot;

/**
 * The Class Action.
 *
 * @author Rob Graff (RGraff1)
 */
public class Action implements IAction {

	/** The type. */
	protected ActionType type = ActionType.Action;

	/** The driver. */
	protected WebDriver driver;
	
	/** The message. */
	protected String message;
	
	/** The wait type. */
	protected WaitType waitType;
	
	/** The wait time. */
	protected int waitTime;

	/** The post conditions. */
	protected List<Condition> postConditions;
	
	/** The pre conditions. */
	protected List<Condition> preConditions;
	
	/** The pre exit. */
	protected Condition preExit;

	/** The screenshot. */
	protected Boolean screenshot;
	
	/** The optional. */
	protected Boolean optional;
	
	/** The step num. */
	protected int stepNum;

	/** The result. */
	private StepResult result;

	/**
	 * Instantiates a new action.
	 *
	 * @param driver the driver
	 * @param message the message
	 * @param waitTime the wait time
	 * @param preConditions the pre conditions
	 * @param postConditions the post conditions
	 * @param screenshot the screenshot
	 * @param optional the optional
	 * @param stepNum the step num
	 */
	public Action(WebDriver driver, String message, int waitTime, List<Condition> preConditions,
			List<Condition> postConditions, Boolean screenshot, Boolean optional, int stepNum) {
		this.driver = driver;
		this.message = message;
		this.waitTime = waitTime;

		this.preConditions = preConditions;
		this.postConditions = postConditions;

		this.screenshot = screenshot;
		this.optional = optional;
		this.stepNum = stepNum;
		this.result = new StepResult(StepStatus.noRun);
		this.preExit = null;
	}

	/**
	 * Instantiates a new action.
	 *
	 * @param driver the driver
	 * @param params the params
	 */
	public Action(WebDriver driver, ActionParams params) {
		this.driver = driver;
		this.message = params.message;
		this.waitType = params.waitType;
		this.waitTime = params.waitTime;

		this.preConditions = params.preConditions;
		this.postConditions = params.postConditions;

		this.screenshot = params.screenshot;
		this.optional = params.optional;
		this.stepNum = params.stepNum;
		this.result = new StepResult(StepStatus.noRun);
		this.preExit = null;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.eqre.auto.sam.action.actions.IAction#type()
	 */
	public ActionType type() {
		return type;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.eqre.auto.sam.action.actions.IAction#pre()
	 */
	public boolean pre() throws Exception {
		if (null == preConditions) {
			return true;
		}
		
		int wait;
		if (null == preExit) {
			wait = 1;
		} else {
			wait = waitTime;
		}
		
		for (int i = 0; i < wait; i++) {
			for (Condition condition : preConditions) {

				try {
					condition.execute();
				} catch (Exception e) {
					if (checkForWindow(e)) {
						throw e;
					}
				}
				try {
					if (null != preExit) {
						if (preExit.check()) {
							return true;
						}
					}
				} catch (Exception e) {
					if (checkForWindow(e)) {
						throw e;
					}
				}
			}
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.eqre.auto.sam.action.actions.IAction#post()
	 */
	public boolean post() {
		if (null != postConditions) {
			try {
				for (Condition condition : postConditions) {
					condition.execute();
				}
				return true;
			} catch (Exception e) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Execute.
	 *
	 * @return true, if successful
	 */
	public boolean execute() {
		result.start();
		try {
			if (pre()) {
				mainAction();
				post();
				result.end(StepStatus.pass);
				return true;
			}
		} catch (Exception e) {
			result.setException(e);
			result.end(StepStatus.fail);
			return false;
		} finally {
			if (screenshot || !result.getStepBool()) {
				try {
					result.setScreenshotLocation(ScreenShot.take(driver, message.replace(" ", "_").trim()));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.eqre.auto.sam.action.actions.IAction#mainAction()
	 */
	public void mainAction() throws Exception {

	}

	/**
	 * Check for window.
	 *
	 * @param e the e
	 * @return true, if successful
	 */
	private boolean checkForWindow(Exception e) {
		if (e.getCause().getClass().isInstance(NoSuchWindowException.class)) {
			if (null == driver) {
				return true;
			}
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "message[" + message + "] driver[" + ((RemoteWebDriver) driver).getSessionId() + "] wait[" + waitTime
				+ "]";
	}

	/**
	 * Gets the step result.
	 *
	 * @return the step result
	 */
	public StepResult getStepResult() {
		return result;
	}
	
	/**
	 * Checks if is optional.
	 *
	 * @return the boolean
	 */
	public Boolean isOptional(){
		return optional;
	}

}
