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

import com.tmobile.selenium.sam.action.actions.Cookie;
import com.tmobile.selenium.sam.action.driver.ActionDriver;
import com.tmobile.selenium.sam.config.ActionConfig;

/**
 * A factory for creating Cookie objects.
 *
 * @author Rob Graff
 */
public class CookieFactory extends ActionFactory<CookieFactory>{

	/** The key. */
	private String key;
	
	/** The value. */
	private String value;

	/**
	 * Instantiates a new cookie factory.
	 *
	 * @param driver the driver
	 * @param appConfig the app config
	 */
	public CookieFactory(WebDriver driver, ActionConfig appConfig) {
		super(driver, appConfig);
	}

	/* (non-Javadoc)
	 * @see com.tmobile.eqre.auto.sam.action.factory.ActionFactory#execute()
	 */
	@Override
	public void execute(){
		Cookie cookie = new Cookie(driver, key, value, actionParams);
		actionDriver.run(cookie);
	}
	
	/**
	 * Key.
	 *
	 * @param key the key
	 * @return the cookie factory
	 */
	public CookieFactory key(String key){
		this.key = key;
		return this;
	}
	
	/**
	 * Value.
	 *
	 * @param value the value
	 * @return the cookie factory
	 */
	public CookieFactory value(String value){
		this.value = value;
		return this;
	}
	
}
