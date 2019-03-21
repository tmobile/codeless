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

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.tmobile.selenium.sam.action.actions.Click;
import com.tmobile.selenium.sam.action.driver.ActionDriver;
import com.tmobile.selenium.sam.action.types.ClickType;
import com.tmobile.selenium.sam.action.types.WaitType;
import com.tmobile.selenium.sam.action.utils.Element;
import com.tmobile.selenium.sam.action.utils.Wait;
import com.tmobile.selenium.sam.config.ActionConfig;

/**
 * A factory for creating Wait objects.
 *
 * @author Rob Graff
 */
public class WaitFactory extends ActionFactory<WaitFactory>{
	
	/** The element. */
	private Element element;
	
	/** The wait type. */
	private WaitType waitType;
	
	/** The wait time. */
	private Integer waitTime;

	/**
	 * Instantiates a new wait factory.
	 *
	 * @param driver the driver
	 * @param appConfig the app config
	 */
	public WaitFactory(WebDriver driver, ActionConfig appConfig) {
		super(driver, appConfig);
		this.waitType = appConfig.waitType;
		this.waitTime = appConfig.waitTime;
		
	}
	
	/* (non-Javadoc)
	 * @see com.tmobile.eqre.auto.sam.action.factory.ActionFactory#execute()
	 */
	@Override
	public void execute(){
		Wait wait = new Wait(this.driver, this.element, this.waitType, this.waitTime);
		try {
			wait.execute();
		} catch (Exception e) {
			RuntimeException ex = new RuntimeException("TimeoutException");
			ex.initCause(e);
			throw ex;
		}
	}
	
	/**
	 * For element.
	 *
	 * @param element the element
	 * @return the wait factory
	 */
	public WaitFactory forElement(WebElement element){
		this.element = new Element(element, appConfig.waitType, actionParams.waitTime);
		return this;
	}

}
