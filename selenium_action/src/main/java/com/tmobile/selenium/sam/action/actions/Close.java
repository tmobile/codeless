package com.tmobile.selenium.sam.action.actions;

import org.openqa.selenium.WebDriver;

public class Close extends Action implements IAction {

	/**
	 * The Class Close.
	 *
	 * @author Sai Chandra Korpu (SKorpu1)
	 */
	public Close(WebDriver driver, ActionParams params) {
		super(driver, params);
	}

	@Override
	public void mainAction() {
		driver.close();
	}
}
