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
import org.openqa.selenium.WebElement;

import com.tmobile.selenium.sam.action.actions.Drag;
import com.tmobile.selenium.sam.action.driver.ActionDriver;
import com.tmobile.selenium.sam.action.utils.Element;
import com.tmobile.selenium.sam.config.ActionConfig;

/**
 * A factory for creating Drag objects.
 *
 * @author Rob Graff
 */
public class DragFactory extends ActionFactory<DragFactory>{

	/** The drag. */
	private Element drag;
	
	/** The drop. */
	private Element drop;
	
	/**
	 * Instantiates a new drag factory.
	 *
	 * @param driver the driver
	 * @param appConfig the app config
	 */
	public DragFactory(WebDriver driver, ActionConfig appConfig) {
		super(driver, appConfig);
	}
	
	/* (non-Javadoc)
	 * @see com.tmobile.eqre.auto.sam.action.factory.ActionFactory#execute()
	 */
	public void execute(){
		Drag dragAction = new Drag(driver, drag, drop, actionParams);
		actionDriver.run(dragAction);
	}
	
	/**
	 * Drag.
	 *
	 * @param element the element
	 * @return the drag factory
	 */
	public DragFactory drag(WebElement element){
		this.drag = new Element(element, appConfig.waitType, actionParams.waitTime);
		return this;
	}
	
	/**
	 * To.
	 *
	 * @param element the element
	 * @return the drag factory
	 */
	public DragFactory to(WebElement element){
		this.drop = new Element(element, appConfig.waitType, actionParams.waitTime);
		return this;
	}

}
