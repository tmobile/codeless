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

import org.openqa.selenium.WebDriver;

import com.tmobile.selenium.sam.action.types.ActionType;

/**
 * The Class Go.
 *
 * @author Rob Graff (RGraff1)
 */
public class Go extends Action {

	/** The url. */
	private String url;

	/**
	 * Instantiates a new go.
	 *
	 * @param driver the driver
	 * @param url the url
	 * @param params the params
	 */
	public Go(WebDriver driver, String url, ActionParams params) {
		super(driver, params);
		this.type = ActionType.Go;
		this.url = url;
		this.preExit = null;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.eqre.auto.sam.action.actions.Action#mainAction()
	 */
	public void mainAction() {

		if (!driver.getWindowHandle().isEmpty()) {
			driver.manage().window().maximize();
			driver.get(url);
		}
	}

	/* (non-Javadoc)
	 * @see com.tmobile.eqre.auto.sam.action.actions.Action#toString()
	 */
	@Override
	public String toString() {
		return super.toString() + " url[" + url + "]";
	}

}
