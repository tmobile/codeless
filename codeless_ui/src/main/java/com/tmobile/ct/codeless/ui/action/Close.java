package com.tmobile.ct.codeless.ui.action;

import java.util.concurrent.Future;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.tmobile.selenium.sam.action.factory.CloseFactory;
import com.tmobile.selenium.sam.config.ActionConfig;

public class Close extends BaseAction implements UiAction {

	/**
	 * The Class Close.
	 *
	 * @author Sai Chandra Korpu (SKorpu1)
	 */
	public Close(Future<WebDriver> driver, ActionConfig config, WebElement element) {
		super(driver, config, element);
	}

	@Override
	public void run() {
		try {
			new CloseFactory(getDriver(), config).execute();
		} catch (Exception e) {
			fail(e);
			throw e;
		}
	}

	@Override
	public void setText(String input) {
		// TODO Auto-generated method stub
	}

}
