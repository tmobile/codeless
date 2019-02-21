package com.tmobile.ct.codeless.ui.action;

import java.util.concurrent.Future;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.tmobile.selenium.sam.action.factory.CookieFactory;
import com.tmobile.selenium.sam.config.ActionConfig;

/**
 * The Class Cookie.
 *
 * @author Rob Graff
 */
public class Cookie extends BaseAction implements UiAction {

	/** The key. */
	private String key;

	/** The value. */
	private String value;

	/**
	 * Instantiates a new cookie.
	 *
	 * @param driver the driver
	 * @param config the config
	 * @param element the element
	 * @param key the key
	 * @param value the value
	 */
	public Cookie(Future<WebDriver> driver, ActionConfig config, WebElement element, String key, String value) {
		super(driver, config, element);
		this.key = key;
		this.value = value;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Executable#run()
	 */
	@Override
	public void run() {

		try{
			new CookieFactory(getDriver(), config).key(key).value(value).execute();
		}catch(Exception e){
			fail(e);
			throw e;
		}
	}

	@Override
	public void setText(String input) {
		// TODO Auto-generated method stub

	}
}
