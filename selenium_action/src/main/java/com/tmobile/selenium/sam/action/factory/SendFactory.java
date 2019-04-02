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

import com.tmobile.selenium.sam.action.actions.Send;
import com.tmobile.selenium.sam.action.driver.ActionDriver;
import com.tmobile.selenium.sam.action.types.SendKeysType;
import com.tmobile.selenium.sam.action.utils.Element;
import com.tmobile.selenium.sam.config.ActionConfig;

/**
 * A factory for creating Send objects.
 *
 * @author Rob Graff
 */
public class SendFactory extends ActionFactory<SendFactory> {

	/** The element. */
	private Element element;
	
	/** The input. */
	private String input;
	
	/** The send keys type. */
	private SendKeysType sendKeysType;

	/**
	 * Instantiates a new send factory.
	 *
	 * @param driver the driver
	 * @param appConfig the app config
	 */
	public SendFactory(WebDriver driver, ActionConfig appConfig) {
		super(driver, appConfig);
		this.sendKeysType = appConfig.sendKeysType;
	}
	
	/* (non-Javadoc)
	 * @see com.tmobile.eqre.auto.sam.action.factory.ActionFactory#execute()
	 */
	public void execute(){
		Send send = new Send(driver, element, input, sendKeysType, actionParams);
		actionDriver.run(send);
	}
	
	/**
	 * Send to.
	 *
	 * @param element the element
	 * @return the send factory
	 */
	public SendFactory sendTo(WebElement element){
		this.element = new Element(element, appConfig.waitType, actionParams.waitTime);
		return this;
	}
	
	/**
	 * Text.
	 *
	 * @param text the text
	 * @return the send factory
	 */
	public SendFactory text(String text){
		this.input = text;
		return this;
	}
	
	/**
	 * Using.
	 *
	 * @param sendKeysType the send keys type
	 * @return the send factory
	 */
	public SendFactory using(SendKeysType sendKeysType){
		this.sendKeysType = sendKeysType;
		return this;
	}

}
