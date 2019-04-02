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

import com.tmobile.selenium.sam.action.actions.Move;
import com.tmobile.selenium.sam.action.types.MoveType;
import com.tmobile.selenium.sam.action.utils.Element;
import com.tmobile.selenium.sam.config.ActionConfig;

public class MoveFactory extends ActionFactory<ClickFactory>{
	
	/** The element. */
	private Element element;
	
	/** The move type. */
	private MoveType moveType;


	public MoveFactory(WebDriver driver, ActionConfig appConfig) {
		super(driver, appConfig);
		this.moveType = appConfig.moveType;
		
	}
	
	@Override
	public void execute(){
		Move click = new Move(this.driver, this.element, this.moveType, this.actionParams);
		actionDriver.run(click);
	}
	
	public MoveFactory move(WebElement element){
		this.element = new Element(element, appConfig.waitType, actionParams.waitTime);
		return this;
	}

}
