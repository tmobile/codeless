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
import org.openqa.selenium.WebElement;

import com.tmobile.selenium.sam.action.utils.Element;
import com.tmobile.selenium.sam.action.utils.Wait;

/**
 * The Class Read.
 *
 * @author Rob Graff (RGraff1)
 */
public class Read extends Action {

	/** The element. */
	private Element element;
	
	/** The output. */
	private String output;

	/**
	 * Instantiates a new read.
	 *
	 * @param driver the driver
	 * @param element the element
	 * @param output the output
	 * @param params the params
	 */
	public Read(WebDriver driver, Element element, String output, ActionParams params) {
		super(driver, params);
		this.element = element;
		this.output = output;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.eqre.auto.sam.action.actions.Action#mainAction()
	 */
	@Override
	public void mainAction() throws Exception {
		if (new Wait(driver, element, waitTime).execute()) {
			WebElement target = element.get();
			output = target.getText();
			
//			TestLifeCycleSupportUtils.logStep(StepStatus.PASS, "READ: " + element.locator(), contents, driver,
//					Reporter.getCurrentTestResult(), false);
		}
	}

	/* (non-Javadoc)
	 * @see com.tmobile.eqre.auto.sam.action.actions.Action#toString()
	 */
	@Override
	public String toString() {
		return super.toString() + " element[" + element.locator() + "] read[" + output + "]";
	}
	
	/**
	 * Return value.
	 *
	 * @return the string
	 */
	public String returnValue(){
		return output;
	}

}
