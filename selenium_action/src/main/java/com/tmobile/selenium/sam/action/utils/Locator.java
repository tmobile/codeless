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
package com.tmobile.selenium.sam.action.utils;

import java.lang.reflect.InvocationTargetException;

import org.openqa.selenium.By;

/**
 * The Class Locator.
 *
 * @author Rob Graff (RGraff1)
 */
public class Locator {

	/** The locator. */
	String locator;
	
	/** The by type. */
	ByType byType;
	
	/**
	 * Instantiates a new locator.
	 *
	 * @param locator the locator
	 * @param check the check
	 */
	public Locator(String locator, ByType check){

		this.locator = locator;
		this.byType = check;
	}
	
	/**
	 * Gets the.
	 *
	 * @return the by
	 */
	public By get(){
		try {
//			System.out.println(ByType.id.name());
			return (By) By.class.getDeclaredMethod (byType.name(), String.class).invoke(null, locator);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return byType.name() + "=" + locator;
	}
	
	/**
	 * The Enum ByType.
	 *
	 * @author Rob Graff
	 */
	public enum ByType{
		
		/** The id. */
		id,
/** The name. */
name,
/** The xpath. */
xpath,
/** The css selector. */
cssSelector
	}
}
