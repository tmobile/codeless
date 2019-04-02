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

import com.tmobile.selenium.sam.action.actions.Select;
import com.tmobile.selenium.sam.action.driver.ActionDriver;
import com.tmobile.selenium.sam.action.types.SelectType;
import com.tmobile.selenium.sam.action.utils.Element;
import com.tmobile.selenium.sam.config.ActionConfig;

/**
 * A factory for creating Select objects.
 *
 * @author Rob Graff
 */
public class SelectFactory extends ActionFactory<SelectFactory>{

	/** The element. */
	private Element element;
	
	/** The input. */
	private String input;
	
	/** The select type. */
	private SelectType selectType;
	
	/**
	 * Instantiates a new select factory.
	 *
	 * @param driver the driver
	 * @param appConfig the app config
	 */
	public SelectFactory(WebDriver driver, ActionConfig appConfig) {
		super(driver, appConfig);
		this.selectType = appConfig.selectType;
	}
	
	/* (non-Javadoc)
	 * @see com.tmobile.eqre.auto.sam.action.factory.ActionFactory#execute()
	 */
	@Override
	public void execute(){
		Select send = new Select(driver, element, input, selectType, actionParams);
		actionDriver.run(send);
	}
	
	/**
	 * Select from.
	 *
	 * @param element the element
	 * @return the select factory
	 */
	public SelectFactory selectFrom(WebElement element){
		this.element = new Element(element, appConfig.waitType, actionParams.waitTime);
		return this;
	}
	
	/**
	 * Item.
	 *
	 * @param text the text
	 * @return the select factory
	 */
	public SelectFactory item(String text){
		this.input = text;
		return this;
	}
	
	/**
	 * Using.
	 *
	 * @param selectType the select type
	 * @return the select factory
	 */
	public SelectFactory using(SelectType selectType){
		this.selectType = selectType;
		return this;
	}

}
