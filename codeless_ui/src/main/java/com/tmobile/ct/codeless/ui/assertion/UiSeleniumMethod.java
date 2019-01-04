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
