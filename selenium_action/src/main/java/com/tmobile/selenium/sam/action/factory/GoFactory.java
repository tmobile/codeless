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

import com.tmobile.selenium.sam.action.actions.Go;
import com.tmobile.selenium.sam.action.driver.ActionDriver;
import com.tmobile.selenium.sam.config.ActionConfig;

/**
 * A factory for creating Go objects.
 *
 * @author Rob Graff
 */
public class GoFactory extends ActionFactory<GoFactory> {
	
	/** The url. */
	private String url;

	/**
	 * Instantiates a new go factory.
	 *
	 * @param driver the driver
	 * @param appConfig the app config
	 */
	public GoFactory(WebDriver driver, ActionConfig appConfig) {
		super(driver, appConfig);
	}
	
	/* (non-Javadoc)
	 * @see com.tmobile.eqre.auto.sam.action.factory.ActionFactory#execute()
	 */
	@Override
	public void execute(){
		Go go = new Go(this.driver, this.url, this.actionParams);
		actionDriver.run(go);
	}
	
	/**
	 * Go.
	 *
	 * @param url the url
	 * @return the go factory
	 */
	public GoFactory go(String url){
		this.url = url;
		return this;
	}

}
