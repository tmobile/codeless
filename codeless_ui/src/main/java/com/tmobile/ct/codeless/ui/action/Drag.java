package com.tmobile.ct.codeless.ui.action;

import java.util.concurrent.Future;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.tmobile.selenium.sam.action.factory.DragFactory;
import com.tmobile.selenium.sam.config.ActionConfig;

/**
 * The Class Drag.
 *
 * @author Rob Graff
 */
public class Drag extends BaseAction implements UiAction {

	/** The target. */
	private WebElement target;

	/**
	 * Instantiates a new drag.
	 *
	 * @param driver the driver
	 * @param config the config
	 * @param element the element
	 * @param target the target
	 */
	public Drag(Future<WebDriver> driver, ActionConfig config, WebElement element, WebElement target) {
		super(driver, config, element);
		this.target = target;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Executable#run()
	 */
	@Override
	public void run() {
		try{
			new DragFactory(getDriver(), config).drag(element).to(target).execute();
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
