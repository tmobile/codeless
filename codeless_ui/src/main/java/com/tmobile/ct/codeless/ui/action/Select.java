package com.tmobile.ct.codeless.ui.action;

import java.util.concurrent.Future;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.tmobile.selenium.sam.action.factory.SelectFactory;
import com.tmobile.selenium.sam.config.ActionConfig;

/**
 * The Class Select.
 *
 * @author Rob Graff
 */
public class Select extends BaseAction implements UiAction {

	/** The text. */
	private String text;

	/**
	 * Instantiates a new select.
	 *
	 * @param driver the driver
	 * @param config the config
	 * @param element the element
	 * @param text the text
	 */
	public Select(Future<WebDriver> driver, ActionConfig config, WebElement element, String text) {
		super(driver, config, element);
		this.text = text;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Executable#run()
	 */
	@Override
	public void run() {
		try{
			new SelectFactory(getDriver(), config).selectFrom(element).item(text).execute();
		}catch(Exception e){
			fail(e);
			throw e;
		}
	}

	@Override
	public void setText(String input) {
		this.text = text;
	}
}
