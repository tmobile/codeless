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
import com.tmobile.selenium.sam.action.driver.ListDriver;

/**
 * The Class Condition.
 *
 * @author Rob Graff (RGraff1)
 */
public class Condition implements ICondition {

	/** The driver. */
	protected WebDriver driver;
	
	/** The actions. */
	protected List<Action> actions;
	
	/**
	 * Instantiates a new condition.
	 *
	 * @param driver the driver
	 * @param actions the actions
	 */
	public Condition(WebDriver driver, List<Action> actions){
		this.driver = driver;
		this.actions = actions;
	}
	
	/**
	 * Instantiates a new condition.
	 *
	 * @param driver the driver
	 */
	public Condition(WebDriver driver){
		this.driver = driver;
		this.actions = null;
	}
	
	/* (non-Javadoc)
	 * @see com.tmobile.eqre.auto.sam.action.actions.conditions.ICondition#check()
	 */
	public boolean check() throws Exception{
		return true;
	}
	
	/* (non-Javadoc)
	 * @see com.tmobile.eqre.auto.sam.action.actions.conditions.ICondition#execute()
	 */
	public boolean execute() throws Exception{
		if(check() && hasAction()){
			System.out.println("found condition");
			new ListDriver(actions).run();
			return true;
		}
		return false;
	}
	
	/**
	 * Checks for action.
	 *
	 * @return true, if successful
	 */
	public boolean hasAction(){
		return (actions != null);
	}
}
