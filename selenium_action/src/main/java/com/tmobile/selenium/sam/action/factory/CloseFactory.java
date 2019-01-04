package com.tmobile.selenium.sam.action.factory;

import org.openqa.selenium.WebDriver;

import com.tmobile.selenium.sam.action.actions.Close;
import com.tmobile.selenium.sam.config.ActionConfig;

public class CloseFactory extends ActionFactory<CloseFactory> {

	/**
	 * A factory for creating Close objects.
	 *
	 * @author Sai Chandra Korpu (SKorpu1)
	 */
	public CloseFactory(WebDriver driver, ActionConfig appConfig) {
		super(driver, appConfig);
	}

	@Override
	public void execute() {
		Close close = new Close(this.driver, this.actionParams);
		actionDriver.run(close);
	}

}
