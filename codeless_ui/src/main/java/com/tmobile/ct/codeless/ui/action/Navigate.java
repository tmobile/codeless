package com.tmobile.ct.codeless.ui.action;

import java.util.concurrent.Future;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.tmobile.selenium.sam.action.factory.GoFactory;
import com.tmobile.selenium.sam.action.factory.WindowFactory;
import com.tmobile.selenium.sam.action.types.NavigateType;
import com.tmobile.selenium.sam.config.ActionConfig;

/**
 * The Class Navigate.
 *
 * @author Rob Graff
 */
public class Navigate extends BaseAction implements UiAction {

	/**
	 * Instantiates a new navigate.
	 *
	 * @param driver the driver
	 * @param config the config
	 * @param element the element
	 */
	public Navigate(Future<WebDriver> driver, ActionConfig config, WebElement element) {
		super(driver, config, element);
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Executable#run()
	 */
	@Override
	public void run() {
		try{
//			new com.tmobile.eqre.auto.sam.action.actions.Navigate(getDriver(), config.navigateType)
		}catch(Exception e){
			fail(e);
			throw e;
		}
	}
}
