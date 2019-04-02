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
package com.tmobile.ct.codeless.ui.model;

/**
 * The Class ControlElement.
 *
 * @author Rob Graff
 */
public class ControlElement {
	
	/** The by. */
	private String by;
	
	/** The locator. */
	private String locator;

	/**
	 * Instantiates a new control element.
	 */
	public ControlElement(){}
	
	/**
	 * Instantiates a new control element.
	 *
	 * @param by the by
	 * @param locator the locator
	 */
	public ControlElement(String by, String locator) {
		this.by = by;
		this.locator = locator;
	}

	/**
	 * Gets the locator.
	 *
	 * @return the locatorValue
	 */
	public String getLocator() {
		return locator;
	}
	
	/**
	 * Sets the locator.
	 *
	 * @param locator the new locator
	 */
	public void setLocator(String locator) {
		this.locator = locator;
	}
	
	/**
	 * Gets the by.
	 *
	 * @return the By type
	 */
	public String getBy() {
		return by;
	}
	
	/**
	 * Sets the by.
	 *
	 * @param by the new by
	 */
	public void setBy(String by) {
		this.by = by;
	}
}
