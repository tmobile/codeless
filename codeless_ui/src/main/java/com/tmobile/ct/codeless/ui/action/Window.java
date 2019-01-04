package com.tmobile.ct.codeless.ui.action;

import java.util.concurrent.Future;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.tmobile.selenium.sam.action.factory.GoFactory;
import com.tmobile.selenium.sam.action.factory.WindowFactory;
import com.tmobile.selenium.sam.config.ActionConfig;

/**
 * The Class Window.
 *
 * @author Rob Graff
 */
public class Window extends BaseAction implements UiAction {

	/** The input. */
	private String input;

	/**
	 * Instantiates a new window.
	 *
	 * @param driver the driver
	 * @param config the config
	 * @param element the element
	 * @param input the input
	 */
	public Window(Future<WebDriver> driver, ActionConfig config, WebElement element, String input) {
		super(driver, config, element);
		this.input = input;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Executable#run()
	 */
	@Override
	public void run() {
		try{
			new WindowFactory(getDriver(), config).window(input).execute();
		}catch(Exception e){
			fail(e);
			throw e;
		}
	}
}
