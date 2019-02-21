package com.tmobile.ct.codeless.ui.action;

import java.util.concurrent.Future;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.tmobile.selenium.sam.action.factory.GoFactory;
import com.tmobile.selenium.sam.config.ActionConfig;

/**
 * The Class GoTo.
 *
 * @author Rob Graff
 */
public class GoTo extends BaseAction implements UiAction {

	/** The url. */
	private String url;

	/**
	 * Instantiates a new go to.
	 *
	 * @param driver the driver
	 * @param config the config
	 * @param element the element
	 * @param url the url
	 */
	public GoTo(Future<WebDriver> driver, ActionConfig config, WebElement element, String url) {
		super(driver, config, element);
		this.url = url;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Executable#run()
	 */
	@Override
	public void run() {
		try{
			new GoFactory(getDriver(), config).go(url).execute();
		}catch(Exception e){
			fail(e);
			throw e;
		}
	}

	@Override
	public void setText(String url) {
		this.url = url;
	}
}
