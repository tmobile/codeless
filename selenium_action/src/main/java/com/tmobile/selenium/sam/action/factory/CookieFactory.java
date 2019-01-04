package com.tmobile.selenium.sam.action.factory;

import org.openqa.selenium.WebDriver;

import com.tmobile.selenium.sam.action.actions.Cookie;
import com.tmobile.selenium.sam.action.driver.ActionDriver;
import com.tmobile.selenium.sam.config.ActionConfig;

/**
 * A factory for creating Cookie objects.
 *
 * @author Rob Graff
 */
public class CookieFactory extends ActionFactory<CookieFactory>{

	/** The key. */
	private String key;
	
	/** The value. */
	private String value;

	/**
	 * Instantiates a new cookie factory.
	 *
	 * @param driver the driver
	 * @param appConfig the app config
	 */
	public CookieFactory(WebDriver driver, ActionConfig appConfig) {
		super(driver, appConfig);
	}

	/* (non-Javadoc)
	 * @see com.tmobile.eqre.auto.sam.action.factory.ActionFactory#execute()
	 */
	@Override
	public void execute(){
		Cookie cookie = new Cookie(driver, key, value, actionParams);
		actionDriver.run(cookie);
	}
	
	/**
	 * Key.
	 *
	 * @param key the key
	 * @return the cookie factory
	 */
	public CookieFactory key(String key){
		this.key = key;
		return this;
	}
	
	/**
	 * Value.
	 *
	 * @param value the value
	 * @return the cookie factory
	 */
	public CookieFactory value(String value){
		this.value = value;
		return this;
	}
	
}
