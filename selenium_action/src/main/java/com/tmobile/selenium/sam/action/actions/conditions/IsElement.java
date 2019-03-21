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
package com.tmobile.selenium.sam.action.actions.conditions;

import java.util.List;

import org.openqa.selenium.WebDriver;

import com.tmobile.selenium.sam.action.actions.Action;
import com.tmobile.selenium.sam.action.utils.Element;
import com.tmobile.selenium.sam.action.utils.Wait;

/**
 * The Class IsElement.
 *
 * @author Rob Graff (RGraff1)
 */
public class IsElement extends Condition implements ICondition{

	/** The element. */
	private Element element;
	
	/** The wait time. */
	private int waitTime;
	
	/**
	 * Instantiates a new checks if is element.
	 *
	 * @param driver the driver
	 * @param element the element
	 * @param waitTime the wait time
	 * @param actions the actions
	 */
	public IsElement(WebDriver driver, Element element, int waitTime, List<Action> actions){
		super(driver, actions);
		this.element = element;
		this.waitTime = waitTime;
	}
	
	/**
	 * Instantiates a new checks if is element.
	 *
	 * @param driver the driver
	 * @param element the element
	 * @param actions the actions
	 */
	public IsElement(WebDriver driver, Element element, List<Action> actions){
		super(driver, actions);
		this.element = element;
		this.waitTime = element.getWaitTime();
	}
	
	/**
	 * Instantiates a new checks if is element.
	 *
	 * @param driver the driver
	 * @param element the element
	 * @param waitTime the wait time
	 */
	public IsElement(WebDriver driver, Element element, int waitTime){
		super(driver);
		this.element = element;
		this.waitTime = waitTime;
	}
	
	/**
	 * Instantiates a new checks if is element.
	 *
	 * @param driver the driver
	 * @param element the element
	 */
	public IsElement(WebDriver driver, Element element){
		super(driver);
		this.element = element;
		this.waitTime = element.getWaitTime();
	}
	
	/* (non-Javadoc)
	 * @see com.tmobile.eqre.auto.sam.action.actions.conditions.Condition#check()
	 */
	@Override
	public boolean check() throws Exception{
		try{
			return new Wait(driver, element, waitTime).execute();
		}catch(Exception e){
			return false;
		}
	}

}
