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
package com.tmobile.ct.codeless.ui.action;

import java.util.concurrent.Future;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.tmobile.selenium.sam.action.factory.DragFactory;
import com.tmobile.selenium.sam.config.ActionConfig;

/**
 * The Class Drag.
 *
 * @author Rob Graff
 */
public class Drag extends BaseAction implements UiAction {

	/** The target. */
	private WebElement target;

	/**
	 * Instantiates a new drag.
	 *
	 * @param driver the driver
	 * @param config the config
	 * @param element the element
	 * @param target the target
	 */
	public Drag(Future<WebDriver> driver, ActionConfig config, WebElement element, WebElement target) {
		super(driver, config, element);
		this.target = target;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Executable#run()
	 */
	@Override
	public void run() {
		try{
			new Wait(driver, config, element).run();
			new DragFactory(getDriver(), config).drag(element).to(target).execute();
		}catch(Exception e){
			fail(e);
			throw e;
		}
	}

	@Override
	public void setText(String input) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return null;
	}
}
