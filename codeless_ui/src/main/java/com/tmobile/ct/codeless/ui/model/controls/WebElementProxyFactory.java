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
package com.tmobile.ct.codeless.ui.model.controls;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.concurrent.Future;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.Locatable;
import org.openqa.selenium.internal.WrapsElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.internal.LocatingElementHandler;

import com.tmobile.ct.codeless.ui.model.ControlElement;

/**
 * A factory for creating WebElementProxy objects.
 *
 * @author Rob Graff
 */
public class WebElementProxyFactory {

	/**
	 * From control element.
	 *
	 * @param searchContext the search context
	 * @param controlElement the control element
	 * @return the web element
	 */
	public WebElement fromControlElement(Future<WebDriver> searchContext, ControlElement controlElement){
		By by = initBy(controlElement);
		return proxyForLocator(new FutureElementLocator(searchContext, by));
	}
	
	/**
	 * Proxy for locator.
	 *
	 * @param locator the locator
	 * @return the web element
	 */
	public WebElement proxyForLocator(ElementLocator locator) {
	    InvocationHandler handler = new LocatingElementHandler(locator);

	    WebElement proxy;
	    proxy = (WebElement) Proxy.newProxyInstance(
	        WebElementProxyFactory.class.getClassLoader(), 
	        new Class[]{WebElement.class, WrapsElement.class, Locatable.class},
	        handler);
	    return proxy;
	  }
	
	/**
	 * Inits the by.
	 *
	 * @param element the element
	 * @return the by
	 */
	public By initBy(ControlElement element) {
		
		String byValue = element.getBy().toLowerCase().trim();
		String locator = element.getLocator();
		
		ByTypes loc = ByTypes.valueOf(byValue);
		switch (loc) {
		case id:
			return By.id(locator);
		case css:
			return By.cssSelector(locator);
		case xpath:
			return By.xpath(locator);
		case tagname:
			return By.tagName(locator);
		case linktext:
			return By.linkText(locator);
		case partiallinktext:
			return By.partialLinkText(locator);
		case name:
			return By.name(locator);
		case classname:
			return By.className(locator);
		default:
			return By.id(locator);
		}
	}
}
