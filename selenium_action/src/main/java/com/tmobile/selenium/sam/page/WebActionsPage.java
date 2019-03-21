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
package com.tmobile.selenium.sam.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.tmobile.selenium.sam.action.factory.ClickFactory;
import com.tmobile.selenium.sam.action.factory.CloseFactory;
import com.tmobile.selenium.sam.action.factory.CookieFactory;
import com.tmobile.selenium.sam.action.factory.DragFactory;
import com.tmobile.selenium.sam.action.factory.FrameFactory;
import com.tmobile.selenium.sam.action.factory.GoFactory;
import com.tmobile.selenium.sam.action.factory.KeyFactory;
import com.tmobile.selenium.sam.action.factory.ReadFactory;
import com.tmobile.selenium.sam.action.factory.SelectFactory;
import com.tmobile.selenium.sam.action.factory.SendFactory;
import com.tmobile.selenium.sam.action.factory.WindowFactory;
import com.tmobile.selenium.sam.config.ActionConfig;

/**
 * The Class WebActionsPage.
 *
 * @author Rob Graff
 */
public class WebActionsPage {
	
	/** The driver. */
	protected WebDriver driver;
	
	/** The app config. */
	private ActionConfig appConfig;


	/**
	 * Instantiates a new web actions page.
	 *
	 * @param driver the driver
	 * @param config the config
	 */
	public WebActionsPage(WebDriver driver, ActionConfig config){
		this.driver = driver;
		this.appConfig = new ActionConfig();
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * Go to url.
	 *
	 * @param url the url
	 * @return the go factory
	 */
	public GoFactory goToUrl(String url){
		return new GoFactory(driver, appConfig).go(url);
	}
	
	/**
	 * Click.
	 *
	 * @param element the element
	 * @return the click factory
	 */
	public ClickFactory click(WebElement element){
		return new ClickFactory(driver, appConfig).click(element);
	}
	
	/**
	 * Send to.
	 *
	 * @param element the element
	 * @return the send factory
	 */
	public SendFactory sendTo(WebElement element){
		return new SendFactory(driver, appConfig).sendTo(element);
	}
	
	/**
	 * Select from.
	 *
	 * @param element the element
	 * @return the select factory
	 */
	public SelectFactory selectFrom(WebElement element){
		return new SelectFactory(driver, appConfig).selectFrom(element);
	}
	
	/**
	 * Read.
	 *
	 * @param element the element
	 * @return the read factory
	 */
	public ReadFactory read(WebElement element){
		return new ReadFactory(driver, appConfig).read(element);
	}
	
	/**
	 * Sets the cookie.
	 *
	 * @param key the key
	 * @return the cookie factory
	 */
	public CookieFactory setCookie(String key){
		return new CookieFactory(driver, appConfig).key(key);
	}

	/**
	 * Gets the frame.
	 *
	 * @param element the element
	 * @return the frame
	 */
	public FrameFactory getFrame(WebElement element){
		return new FrameFactory(driver, appConfig).frame(element);
	}
	
	/**
	 * Gets the window.
	 *
	 * @param input the input
	 * @return the window
	 */
	public WindowFactory getWindow(String input){
		return new WindowFactory(driver, appConfig).window(input);
	}
	
	/**
	 * Drag.
	 *
	 * @param element the element
	 * @return the drag factory
	 */
	public DragFactory drag(WebElement element){
		return new DragFactory(driver, appConfig).drag(element);
	}
	
	/**
	 * Send key to.
	 *
	 * @param element the element
	 * @return the key factory
	 */
	public KeyFactory sendKeyTo(WebElement element){
		return new KeyFactory(driver, appConfig).sendKeyTo(element);
	}
	
	/**
	 * Close the browser
	 *
	 * @return the close factory
	 */
	public CloseFactory close() {
		return new CloseFactory(driver, appConfig);
	}
}
