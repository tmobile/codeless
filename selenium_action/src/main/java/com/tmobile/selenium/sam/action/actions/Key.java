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

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import com.tmobile.selenium.sam.action.actions.conditions.IsElement;
import com.tmobile.selenium.sam.action.types.ActionType;
import com.tmobile.selenium.sam.action.utils.Element;

/**
 * The Class Key.
 *
 * @author Rob Graff (RGraff1)
 */
public class Key extends Action implements IAction{

	/** The element. */
	private Element element;
	
	/** The key. */
	private Keys key;

	/**
	 * Instantiates a new key.
	 *
	 * @param driver the driver
	 * @param element the element
	 * @param key the key
	 * @param params the params
	 */
	public Key(WebDriver driver, Element element, Keys key, ActionParams params) {
		super(driver, params);
		this.element = element;
		this.key = key;
		this.type = ActionType.Key;
		this.preExit = new IsElement(driver, element, 1);

	}
	
	@Override
	public void mainAction() throws Exception {
		if(element != null){
			new Actions(driver).sendKeys(element.get(), key ).build().perform();
		}else{
			new Actions(driver).sendKeys(key).build().perform();
		}
	}
	
	@Override
	public String toString() {
		String output = " key["+key.name()+"]";
		if(element != null){
			output = " element[" + element.locator() + "]" + output;
		}
		return super.toString() + output;
	}

}
