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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tmobile.ct.codeless.core.Test;

/**
 * The Class UiAssertion.
 *
 * @author Sai Chandra Korpu
 */
public class UiAssertion {
	
	private static Logger log = LoggerFactory.getLogger(UiAssertion.class);
	
	public static void buildAssertions(Test test, List<UiAssertionBuilder> assertions, WebElement webElement) {
		assertions.forEach(assertion -> {
			Method assertionMethod = assertion.getAssertMethod();
			Method seleniumMethod = assertion.getSeleniumMethod();
			Object ElementorDriver;
			if (assertion.getSeleniumMethodType() == SeleniumMethodType.WebDriver) {
				ElementorDriver = test.getWebDriver();
			} else {
				ElementorDriver = webElement;
			}
			try {
				if (assertionMethod.getParameterCount() == 1) {
					if (seleniumMethod == null) {
						assertionMethod.invoke(null, ElementorDriver);
						log.info("Assertion [ " + assertionMethod + " ] Succesful");
					} else if (seleniumMethod.getParameterCount() == 0) {
						
						assertionMethod.invoke(null, seleniumMethod.invoke(ElementorDriver));
						log.info("Assertion [ " + assertionMethod + " ] Succesful on the [ " + seleniumMethod + " ]");
					} else if (seleniumMethod.getParameterCount() == 1) {
						
						assertionMethod.invoke(null,
								seleniumMethod.invoke(ElementorDriver, assertion.getParameterName()));
						log.info("Assertion [ " + assertionMethod + " ] Succesful on the [ " + seleniumMethod + " ] with parameterName ");
					}
				} else if (assertionMethod.getParameterCount() == 2) {
					String expected = assertion.getExpectedValue();
					if (assertion.getNumberFormat() != null && !assertion.getNumberFormat().isEmpty()){
						long number = Long.valueOf(expected);
						expected = String.format(assertion.getNumberFormat(), number);
					}
					if (seleniumMethod.getParameterCount() == 0) {
						assertionMethod.invoke(null, seleniumMethod.invoke(ElementorDriver),
									expected);
						log.info("Assertion [ " + assertionMethod + " ] Succesful on the [ " + seleniumMethod + " ]");
					} else if (seleniumMethod.getParameterCount() == 1) {
						
						assertionMethod.invoke(null,
								seleniumMethod.invoke(ElementorDriver, assertion.getParameterName()),
								expected);
						log.info("Assertion [ " + assertionMethod + " ] Succesful on the [ " + seleniumMethod + " ] with parameterName ");
					}
				}

			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				throw new RuntimeException("Assertion Failed " + e.getCause());
			}
		});
	}

}
