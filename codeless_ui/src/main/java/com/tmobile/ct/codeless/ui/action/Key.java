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

import com.tmobile.selenium.sam.action.factory.KeyFactory;
import com.tmobile.selenium.sam.config.ActionConfig;

public class Key extends BaseAction implements UiAction {

	public Key(Future<WebDriver> driver, ActionConfig config, WebElement element) {
		super(driver, config, element);
	}

	@Override
	public void run() {
		
		try{
			new Wait(driver, config, element).run();
			new KeyFactory(getDriver(), config).sendKeyTo(element).key(config.keyType).execute();
		}catch(Exception e){
			fail(e);
			throw e;
		}
	}

	@Override
	public void setText(String input) {
		
	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return null;
	}
}
