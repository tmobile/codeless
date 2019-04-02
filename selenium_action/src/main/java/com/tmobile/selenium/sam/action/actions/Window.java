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

import java.util.Set;

import org.openqa.selenium.WebDriver;

import com.tmobile.selenium.sam.action.types.ActionType;
import com.tmobile.selenium.sam.action.types.WindowType;

/**
 * The Class Window.
 *
 * @author Rob Graff (RGraff1)
 */
public class Window extends Action implements IAction {

	/** The input. */
	private String input;
	
	/** The window type. */
	private WindowType windowType;

	/**
	 * Instantiates a new window.
	 *
	 * @param driver the driver
	 * @param input the input
	 * @param windowType the window type
	 * @param params the params
	 */
	public Window(WebDriver driver, String input, WindowType windowType, ActionParams params) {

		super(driver, params);
		this.type = ActionType.Window;
		this.input = input;
		this.windowType = windowType;
		this.preExit = null;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.eqre.auto.sam.action.actions.Action#mainAction()
	 */
	@Override
	public void mainAction() throws Exception {
		for(int i = 0; i < waitTime; i++){
			if((boolean) Window.class.getDeclaredMethod(windowType.name()).invoke(this)){
				break;
			}
		}

	}

	/**
	 * Title.
	 *
	 * @return true, if successful
	 */
	public boolean title() {

		Set<String> allWindows = driver.getWindowHandles();
		for (String currentWindow : allWindows) {
			if (driver.switchTo().window(currentWindow).getTitle().contains(input)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Url.
	 *
	 * @return true, if successful
	 */
	public boolean url() {
		Set<String> allWindows = driver.getWindowHandles();
		for (String currentWindow : allWindows) {
			if (driver.switchTo().window(currentWindow).getCurrentUrl().contains(input)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * First.
	 *
	 * @return true, if successful
	 */
	public boolean first(){
		
		driver.switchTo().window(driver.getWindowHandles().iterator().next());
		return true;
		
	}

	/* (non-Javadoc)
	 * @see com.tmobile.eqre.auto.sam.action.actions.Action#toString()
	 */
	@Override
	public String toString() {
		return super.toString() + " windowTitle[" + input + "]";
	}
}
