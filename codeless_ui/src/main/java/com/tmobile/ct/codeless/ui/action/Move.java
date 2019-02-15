package com.tmobile.ct.codeless.ui.action;

import java.util.concurrent.Future;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.tmobile.selenium.sam.action.factory.MoveFactory;
import com.tmobile.selenium.sam.config.ActionConfig;

public class Move extends BaseAction implements UiAction {

	public Move(Future<WebDriver> driver, ActionConfig config, WebElement element) {
		super(driver, config, element);
	}

	@Override
	public void run() {
		
		try{
			new MoveFactory(getDriver(), config).move(element).execute();
		}catch(Exception e){
			fail(e);
			throw e;
		}
	}
}
