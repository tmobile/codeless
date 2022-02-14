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
package com.tmobile.selenium.sam.action.utils;

import java.lang.reflect.InvocationTargetException;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tmobile.selenium.sam.action.actions.IAction;
import com.tmobile.selenium.sam.action.types.ActionType;
import com.tmobile.selenium.sam.action.types.WaitType;

/**
 * The Class Wait.
 *
 * @author Rob Graff (RGraff1)
 */
public class Wait implements IAction{

	/** The driver. */
	private WebDriver driver;
	
	/** The element. */
	private Element element;
	
	/** The wait time. */
	private int waitTime;
	
	/** The wait type. */
	private WaitType waitType;
	
	/**
	 * Instantiates a new wait.
	 *
	 * @param driver the driver
	 * @param element the element
	 */
	public Wait(WebDriver driver, Element element){
		this.driver = driver;
		this.element = element;
		this.waitTime = element.getWaitTime();
		this.waitType = element.getWaitType();
	}
	
	/**
	 * Instantiates a new wait.
	 *
	 * @param driver the driver
	 * @param element the element
	 * @param waitTime the wait time
	 */
	public Wait(WebDriver driver, Element element, int waitTime){
		this.driver = driver;
		this.element = element;
		this.waitTime = waitTime;
		this.waitType = element.getWaitType();
	}
	
	/**
	 * Instantiates a new wait.
	 *
	 * @param driver the driver
	 * @param element the element
	 * @param waitType the wait type
	 * @param waitTime the wait time
	 */
	public Wait(WebDriver driver, Element element, WaitType waitType, int waitTime){
		this.driver = driver;
		this.element = element;
		this.waitType = waitType;
		this.waitTime = waitTime;
		
	}
	
	/**
	 * Instantiates a new wait.
	 *
	 * @param driver the driver
	 * @param waitType the wait type
	 * @param waitTime the wait time
	 */
	public Wait(WebDriver driver, WaitType waitType, int waitTime){
		this.driver = driver;
		this.waitType = waitType;
		this.waitTime = waitTime;
	}
	
	/**
	 * Execute.
	 *
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	public boolean execute() throws Exception{
		try{
			Wait.class.getDeclaredMethod(waitType.name()).invoke(this);
			return true;
		} catch (InvocationTargetException e) {
			throw (Exception) e.getCause();
		}
	}
	
	/**
	 * Visible.
	 */
	private void visible(){
		new WebDriverWait(driver, Duration.ofSeconds(waitTime)).until(ExpectedConditions.visibilityOf(element.get()));
	}
	
	/**
	 * Clickable.
	 */
	private void clickable(){
		new WebDriverWait(driver, Duration.ofSeconds(waitTime)).until(ExpectedConditions.elementToBeClickable(element.get()));
	}
	
	/**
	 * Enabled.
	 */
	private void enabled(){
		new WebDriverWait(driver, Duration.ofSeconds(waitTime)).until(
				new ExpectedCondition<Boolean>() {
					public Boolean apply(WebDriver wdriver) {
						return element.get().isEnabled();
					}
				});
	}
	
	/**
	 * I frame.
	 */
	private void iFrame(){
		new WebDriverWait(driver, Duration.ofSeconds(waitTime)).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element.get()));
	}
	
	/**
	 * Window.
	 */
	private void window(){
		new WebDriverWait(driver, Duration.ofSeconds(waitTime)).until(ExpectedConditions.numberOfWindowsToBe(2));
	}
	
	/**
	 * Not visible.
	 */
	private void notVisible(){
		new WebDriverWait(driver, Duration.ofSeconds(waitTime)).until(ExpectedConditions.not(ExpectedConditions.visibilityOf(element.get())));
	}
	
	/**
	 * waitForPageLoad.
	 */
	private void waitForPageLoad() {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
		wait.until(pageLoadCondition);

	}
	
	/**
	 * browserSleep.
	 */
	private void browserSleep() {

		try {
			Thread.sleep(waitTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.tmobile.eqre.auto.sam.action.actions.IAction#type()
	 */
	@Override
	public ActionType type() {
		return ActionType.Wait;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.eqre.auto.sam.action.actions.IAction#pre()
	 */
	@Override
	public boolean pre() throws Exception {
		return true;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.eqre.auto.sam.action.actions.IAction#post()
	 */
	@Override
	public boolean post() {
		return true;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.eqre.auto.sam.action.actions.IAction#mainAction()
	 */
	@Override
	public void mainAction() throws Exception {
		execute();
		
	}
	
}
