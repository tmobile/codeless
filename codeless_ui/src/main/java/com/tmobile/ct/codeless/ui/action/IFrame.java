package com.tmobile.ct.codeless.ui.action;

import java.util.concurrent.Future;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.tmobile.selenium.sam.action.factory.FrameFactory;
import com.tmobile.selenium.sam.config.ActionConfig;

/**
 * The Class IFrame.
 *
 * @author Rob Graff
 */
public class IFrame extends BaseAction implements UiAction {

	/**
	 * Instantiates a new i frame.
	 *
	 * @param driver the driver
	 * @param config the config
	 * @param element the element
	 */
	public IFrame(Future<WebDriver> driver, ActionConfig config, WebElement element) {
		super(driver, config, element);
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Executable#run()
	 */
	@Override
	public void run() {

		try{
			new FrameFactory(getDriver(), config).frame(element).execute();
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
