package com.tmobile.ct.codeless.ui.action;

import java.util.concurrent.Future;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.tmobile.selenium.sam.action.factory.WaitFactory;
import com.tmobile.selenium.sam.config.ActionConfig;

/**
 * The Class Wait.
 *
 * @author Rob Graff
 */
public class Wait extends BaseAction implements UiAction {

	/**
	 * Instantiates a new wait.
	 *
	 * @param driver the driver
	 * @param config the config
	 * @param element the element
	 */
	public Wait(Future<WebDriver> driver, ActionConfig config, WebElement element) {
		super(driver, config, element);
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Executable#run()
	 */
	@Override
	public void run() {

		try{
			new WaitFactory(getDriver(), config).forElement(element).execute();
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
