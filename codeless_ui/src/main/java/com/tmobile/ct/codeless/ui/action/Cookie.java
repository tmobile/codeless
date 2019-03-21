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
package com.tmobile.ct.codeless.ui.action;

import java.util.concurrent.Future;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.tmobile.selenium.sam.action.factory.CookieFactory;
import com.tmobile.selenium.sam.config.ActionConfig;

/**
 * The Class Cookie.
 *
 * @author Rob Graff
 */
public class Cookie extends BaseAction implements UiAction {

	/** The key. */
	private String key;

	/** The value. */
	private String value;

	/**
	 * Instantiates a new cookie.
	 *
	 * @param driver the driver
	 * @param config the config
	 * @param element the element
	 * @param key the key
	 * @param value the value
	 */
	public Cookie(Future<WebDriver> driver, ActionConfig config, WebElement element, String key, String value) {
		super(driver, config, element);
		this.key = key;
		this.value = value;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Executable#run()
	 */
	@Override
	public void run() {

		try{
			new CookieFactory(getDriver(), config).key(key).value(value).execute();
		}catch(Exception e){
			fail(e);
			throw e;
		}
	}

	@Override
	public void setText(String input) {
		// TODO Auto-generated method stub

	}
}
