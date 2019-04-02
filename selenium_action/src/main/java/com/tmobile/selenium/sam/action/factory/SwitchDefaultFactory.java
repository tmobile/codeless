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

import com.tmobile.selenium.sam.action.actions.SwitchDefault;
import com.tmobile.selenium.sam.config.ActionConfig;

/**
 * A factory for creating SwitchDefault objects.
 *
 * @author Rob Graff
 */
public class SwitchDefaultFactory extends ActionFactory<SwitchDefaultFactory> {

	/**
	 * Instantiates a new switch default factory.
	 *
	 * @param driver the driver
	 * @param appConfig the app config
	 */
	public SwitchDefaultFactory(WebDriver driver, ActionConfig appConfig) {
		super(driver, appConfig);
	}
	
	/* (non-Javadoc)
	 * @see com.tmobile.eqre.auto.sam.action.factory.ActionFactory#execute()
	 */
	@Override
	public void execute(){
		SwitchDefault switchDefault = new SwitchDefault(this.driver, this.actionParams);
		actionDriver.run(switchDefault);
	}
	
	/**
	 * Switch default.
	 *
	 * @return the switch default factory
	 */
	public SwitchDefaultFactory switchDefault(){
		return this;
	}

}
