/**
 * 
 */
package com.tmobile.selenium.sam.action.actions;

import org.openqa.selenium.WebDriver;

import com.tmobile.selenium.sam.action.types.ActionType;

/**
 * The Class Cookie.
 *
 * @author Rob Graff (RGraff1)
 */
public class Cookie extends Action implements IAction {

	/** The key. */
	private String key;
	
	/** The value. */
	private String value;

	/**
	 * Instantiates a new cookie.
	 *
	 * @param driver the driver
	 * @param key the key
	 * @param value the value
	 * @param params the params
	 */
	public Cookie(WebDriver driver, String key, String value, ActionParams params) {
		super(driver, params);
		this.type = ActionType.Cookie;
		this.key = key;
		this.value = value;
		this.preExit = null;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.eqre.auto.sam.action.actions.Action#mainAction()
	 */
	@Override
	public void mainAction() {

		org.openqa.selenium.Cookie cookie = new org.openqa.selenium.Cookie(key, value);
		driver.manage().addCookie(cookie);

	}
	
	/* (non-Javadoc)
	 * @see com.tmobile.eqre.auto.sam.action.actions.Action#toString()
	 */
	@Override
	public String toString() {
		return super.toString() + " key[" + key + "] value["+value+"]";
	}

}
