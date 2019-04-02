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
package com.tmobile.selenium.sam.action.factory;

import java.util.List;

import org.openqa.selenium.WebDriver;

import com.tmobile.selenium.sam.action.actions.Action;
import com.tmobile.selenium.sam.action.actions.ActionParams;
import com.tmobile.selenium.sam.action.actions.conditions.Condition;
import com.tmobile.selenium.sam.action.driver.ActionDriver;
import com.tmobile.selenium.sam.action.driver.ActionDriverManager;
import com.tmobile.selenium.sam.action.types.WaitType;
import com.tmobile.selenium.sam.config.ActionConfig;

/**
 * A factory for creating Action objects.
 *
 * @author Rob Graff
 * @param <T> the generic type
 */
public class ActionFactory<T> {

	/** The app config. */
	protected ActionConfig appConfig;
	
	/** The action params. */
	protected ActionParams actionParams;
	
	/** The driver. */
	protected WebDriver driver;
	
	/** The action driver. */
	protected ActionDriver actionDriver;

	/**
	 * Instantiates a new action factory.
	 *
	 * @param driver the driver
	 * @param appConfig the app config
	 */
	public ActionFactory(WebDriver driver, ActionConfig appConfig){
		this.driver = driver;
		this.appConfig = appConfig;
		this.actionParams = new ActionParams(appConfig.message, 
				appConfig.waitType,
				appConfig.waitTime,
				appConfig.preConditions,
				appConfig.postConditions,
				appConfig.screenshot,
				appConfig.optional,
				0,
				appConfig.sendKeysDelay);
		actionDriver = ActionDriverManager.getActionDriver();
	}
	
	/**
	 * Execute.
	 */
	public void execute() {
		new Action(driver, actionParams).execute();
	}
	
	/**
	 * Log message.
	 *
	 * @param message the message
	 * @return the t
	 */
	public T logMessage(String message){
		this.actionParams.message = message;
		return (T) this;
	}
	
	/**
	 * Wait by.
	 *
	 * @param waitType the wait type
	 * @return the t
	 */
	public T waitBy(WaitType waitType){
		actionParams.waitType = waitType;
		return (T) this;
	}
	
	/**
	 * Wait for.
	 *
	 * @param time the time
	 * @return the t
	 */
	public T waitFor(int time){
		actionParams.waitTime = time;
		return (T) this;
	}
	
	/**
	 * Check before.
	 *
	 * @param preConditions the pre conditions
	 * @return the t
	 */
	public T checkBefore(List<Condition> preConditions){
		actionParams.preConditions = preConditions;
		return (T) this;
	}
	
	/**
	 * Check after.
	 *
	 * @param postConditions the post conditions
	 * @return the t
	 */
	public T checkAfter(List<Condition> postConditions){
		actionParams.postConditions = postConditions;
		return (T) this;
	}
	
	/**
	 * Screenshot.
	 *
	 * @return the t
	 */
	public T screenshot(){
		actionParams.screenshot = true;
		return (T) this;
	}
	
	/**
	 * Optional.
	 *
	 * @return the t
	 */
	public T optional(){
		actionParams.optional = true;
		return (T) this;
	}
}
