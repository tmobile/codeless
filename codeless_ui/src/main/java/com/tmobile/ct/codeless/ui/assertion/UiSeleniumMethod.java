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
package com.tmobile.ct.codeless.ui.assertion;

import java.lang.reflect.Method;

/**
 * The Class UiSeleniumMethod.
 *
 * @author Rob Graff
 */
public class UiSeleniumMethod {
	
	/**
	 * Gets the selenium method.
	 *
	 * @param seleniumMethodName the selenium method name
	 * @param parameter the parameter
	 * @param type the type
	 * @return the selenium method
	 * @throws NoSuchMethodException the no such method exception
	 */
	public static Method getSeleniumMethod(String seleniumMethodName, String parameter, SeleniumMethodType type) throws NoSuchMethodException {
		if(type == SeleniumMethodType.WebDriver) {
			if (parameter.trim().length() == 0) {
				return org.openqa.selenium.WebDriver.class.getDeclaredMethod(seleniumMethodName);
			} else {
				return org.openqa.selenium.WebDriver.class.getDeclaredMethod(seleniumMethodName, String.class);
			}
			
		}else if(type == SeleniumMethodType.WebElement) {
			if (parameter.trim().length() == 0) {
				return org.openqa.selenium.WebElement.class.getDeclaredMethod(seleniumMethodName);
			} else {
				return org.openqa.selenium.WebElement.class.getDeclaredMethod(seleniumMethodName, String.class);
			}
		}
		throw new NoSuchMethodException("Selenium Method not exists");
		
	}

}
