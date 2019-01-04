package com.tmobile.selenium.sam.action.factory;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.tmobile.selenium.sam.action.actions.Key;
import com.tmobile.selenium.sam.action.actions.Send;
import com.tmobile.selenium.sam.action.driver.ActionDriver;
import com.tmobile.selenium.sam.action.utils.Element;
import com.tmobile.selenium.sam.config.ActionConfig;

/**
 * A factory for creating Key objects.
 *
 * @author Rob Graff
 */
public class KeyFactory extends ActionFactory<KeyFactory> {

	/** The element. */
	private Element element;
	
	/** The key. */
	private Keys key;
	
	/**
	 * Instantiates a new key factory.
	 *
	 * @param driver the driver
	 * @param appConfig the app config
	 */
	public KeyFactory(WebDriver driver, ActionConfig appConfig) {
		super(driver, appConfig);
	}
	
	/* (non-Javadoc)
	 * @see com.tmobile.eqre.auto.sam.action.factory.ActionFactory#execute()
	 */
	public void execute(){
		Key key = new Key(driver, this.element, this.key, actionParams);
		actionDriver.run(key);
	}

	/**
	 * Send key to.
	 *
	 * @param element the element
	 * @return the key factory
	 */
	public KeyFactory sendKeyTo(WebElement element){
		this.element = new Element(element, appConfig.waitType, actionParams.waitTime);
		return this;
	}
	
	/**
	 * Key.
	 *
	 * @param key the key
	 * @return the key factory
	 */
	public KeyFactory key(Keys key){
		this.key = key;
		return this;
	}
}
