package com.tmobile.ct.codeless.ui.action;

import java.util.concurrent.Future;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.tmobile.selenium.sam.action.factory.ClickFactory;
import com.tmobile.selenium.sam.action.factory.GoFactory;
import com.tmobile.selenium.sam.action.factory.SendFactory;
import com.tmobile.selenium.sam.config.ActionConfig;

/**
 * The Class Type.
 *
 * @author Rob Graff
 */
public class Type extends BaseAction implements UiAction {

	/** The text. */
	private String text;

	/**
	 * Instantiates a new type.
	 *
	 * @param driver the driver
	 * @param config the config
	 * @param element the element
	 * @param text the text
	 */
	public Type(Future<WebDriver> driver, ActionConfig config, WebElement element, String text) {
		super(driver, config, element);
		this.text = text;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Executable#run()
	 */
	@Override
	public void run() {
		try{
			new SendFactory(getDriver(), config).sendTo(element).text(text).execute();
		}catch(Exception e){
			fail(e);
			throw e;
		}
	}
}
