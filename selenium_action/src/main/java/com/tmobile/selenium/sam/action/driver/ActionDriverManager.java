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
package com.tmobile.selenium.sam.action.driver;

/**
 * The Class ActionDriverManager.
 *
 * @author Rob Graff
 */
public class ActionDriverManager {

	/** The action drivers. */
	private static ThreadLocal<ActionDriver> actionDrivers = new ThreadLocal<ActionDriver>();
	
	/**
	 * Gets the action driver.
	 *
	 * @return the action driver
	 */
	public static ActionDriver getActionDriver(){
		if(null == actionDrivers.get()){
			setActionDriver(new ActionDriver());
		}
		return actionDrivers.get();
	}
	
	/**
	 * Sets the action driver.
	 *
	 * @param driver the new action driver
	 */
	public static void setActionDriver(ActionDriver driver){
		actionDrivers.set(driver);
	}
}
