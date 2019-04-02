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

import java.lang.reflect.InvocationTargetException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.tmobile.selenium.sam.action.actions.conditions.IsElement;
import com.tmobile.selenium.sam.action.types.ActionType;
import com.tmobile.selenium.sam.action.types.SendKeysType;
import com.tmobile.selenium.sam.action.utils.Element;
import com.tmobile.selenium.sam.action.utils.Locator;
import com.tmobile.selenium.sam.action.utils.Wait;

/**
 * The Class Send.
 *
 * @author Rob Graff (RGraff1)
 */
public class Send extends Action implements IAction {

	/** The locator. */
	private Locator locator;
	
	/** The element. */
	private Element element;
	
	/** The input. */
	private String input;
	
	/** The send keys type. */
	private SendKeysType sendKeysType;
	
	/** The send keys delay. */
	private long sendKeysDelay;

	/**
	 * Instantiates a new send.
	 *
	 * @param driver the driver
	 * @param element the element
	 * @param input the input
	 * @param sendKeysType the send keys type
	 * @param params the params
	 */
	public Send(WebDriver driver, Element element, String input, SendKeysType sendKeysType, ActionParams params) {
		super(driver, params);
		this.type = ActionType.Send;
		this.input = input;
		this.element = element;
		this.sendKeysType = sendKeysType;
		this.preExit = new IsElement(driver, element, 1);
		this.sendKeysDelay = params.sendKeysDelay;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.eqre.auto.sam.action.actions.Action#mainAction()
	 */
	@Override
	public void mainAction() throws Exception {
		try {
			if (new Wait(driver, element, waitTime).execute()) {
				Send.class.getDeclaredMethod(sendKeysType.name()).invoke(this);
			}
		} catch (InvocationTargetException e) {
			throw (Exception) e.getCause();
		}
	}

	/**
	 * Send keys.
	 */
	public void sendKeys() {
		WebElement target = element.get();
		target.clear();
		target.sendKeys(input);
	}

	/**
	 * Delayed.
	 */
	public void delayed() {
		WebElement target = element.get();
		for (char inputChar: input.toCharArray() ){
			try {
				Thread.sleep(sendKeysDelay);
			} catch (InterruptedException e) {}
			target.sendKeys(String.valueOf(inputChar));
		}
	}

	/* (non-Javadoc)
	 * @see com.tmobile.eqre.auto.sam.action.actions.Action#toString()
	 */
	@Override
	public String toString() {
		return super.toString() + " sendType["+sendKeysType.name()+"] element[" + element.locator() + "] text[" + input + "]";
	}

}
