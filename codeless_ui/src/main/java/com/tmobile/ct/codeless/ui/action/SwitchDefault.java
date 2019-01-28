package com.tmobile.ct.codeless.ui.action;

import java.util.concurrent.Future;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.tmobile.selenium.sam.action.factory.SwitchDefaultFactory;
import com.tmobile.selenium.sam.config.ActionConfig;

/**
 * The Class SwitchDefault.
 *
 * @author Rob Graff
 */
public class SwitchDefault extends BaseAction implements UiAction {


	/**
	 * Instantiates a new switch default.
	 *
	 * @param driver the driver
	 * @param config the config
	 * @param element the element
	 */
	public SwitchDefault(Future<WebDriver> driver, ActionConfig config, WebElement element) {
		super(driver, config, element);
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Executable#run()
	 */
	@Override
	public void run() {
		try{
			new SwitchDefaultFactory(getDriver(), config).execute();
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
