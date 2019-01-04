package com.tmobile.ct.codeless.ui.action;

import java.util.concurrent.Future;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.tmobile.selenium.sam.action.factory.ClickFactory;
import com.tmobile.selenium.sam.config.ActionConfig;

/**
 * The Class Click.
 *
 * @author Rob Graff
 */
public class Click extends BaseAction implements UiAction {

	/**
	 * Instantiates a new click.
	 *
	 * @param driver the driver
	 * @param config the config
	 * @param element the element
	 */
	public Click(Future<WebDriver> driver, ActionConfig config, WebElement element) {
		super(driver, config, element);
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Executable#run()
	 */
	@Override
	public void run() {
		
		try{
			new ClickFactory(getDriver(), config).click(element).execute();
		}catch(Exception e){
			fail(e);
			throw e;
		}
	}
}
