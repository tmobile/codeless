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

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.tmobile.selenium.sam.action.actions.Key;
import com.tmobile.selenium.sam.action.actions.Send;
import com.tmobile.selenium.sam.action.driver.ActionDriver;
import com.tmobile.selenium.sam.action.utils.Element;
import com.tmobile.selenium.sam.config.ActionConfig;

/**
 * A factory for creating Key objects.
 *
 * @author Rob Graff
 */
public class KeyFactory extends ActionFactory<KeyFactory> {

	/** The element. */
	private Element element;
	
	/** The key. */
	private Keys key;
	
	/**
	 * Instantiates a new key factory.
	 *
	 * @param driver the driver
	 * @param appConfig the app config
	 */
	public KeyFactory(WebDriver driver, ActionConfig appConfig) {
		super(driver, appConfig);
	}
	
	/* (non-Javadoc)
	 * @see com.tmobile.eqre.auto.sam.action.factory.ActionFactory#execute()
	 */
	public void execute(){
		Key key = new Key(driver, this.element, this.key, actionParams);
		actionDriver.run(key);
	}

	/**
	 * Send key to.
	 *
	 * @param element the element
	 * @return the key factory
	 */
	public KeyFactory sendKeyTo(WebElement element){
		this.element = new Element(element, appConfig.waitType, actionParams.waitTime);
		return this;
	}
	
	/**
	 * Key.
	 *
	 * @param key the key
	 * @return the key factory
	 */
	public KeyFactory key(Keys key){
		this.key = key;
		return this;
	}
}
