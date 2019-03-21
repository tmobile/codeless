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

import com.tmobile.selenium.sam.action.actions.Select;
import com.tmobile.selenium.sam.action.actions.Window;
import com.tmobile.selenium.sam.action.driver.ActionDriver;
import com.tmobile.selenium.sam.action.types.WindowType;
import com.tmobile.selenium.sam.config.ActionConfig;

/**
 * A factory for creating Window objects.
 *
 * @author Rob Graff
 */
public class WindowFactory extends ActionFactory<WindowFactory> {

	/** The input. */
	private String input;
	
	/** The window type. */
	private WindowType windowType;
	
	/**
	 * Instantiates a new window factory.
	 *
	 * @param driver the driver
	 * @param appConfig the app config
	 */
	public WindowFactory(WebDriver driver, ActionConfig appConfig) {
		super(driver, appConfig);
		this.windowType = appConfig.windowType;
	}
	
	/* (non-Javadoc)
	 * @see com.tmobile.eqre.auto.sam.action.factory.ActionFactory#execute()
	 */
	@Override
	public void execute(){
		Window window = new Window(driver, input, windowType, actionParams);
		actionDriver.run(window);
	}
	
	/**
	 * Window.
	 *
	 * @param input the input
	 * @return the window factory
	 */
	public WindowFactory window(String input){
		this.input = input;
		return this;
	}
	
	/**
	 * Using.
	 *
	 * @param windowType the window type
	 * @return the window factory
	 */
	public WindowFactory using(WindowType windowType){
		this.windowType = windowType;
		return this;
	}

}
