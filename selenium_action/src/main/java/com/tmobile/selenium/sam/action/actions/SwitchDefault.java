package com.tmobile.selenium.sam.action.actions;

import org.openqa.selenium.WebDriver;

import com.tmobile.selenium.sam.action.types.ActionType;

/**
 * The Class SwitchDefault.
 *
 * @author Rob Graff (RGraff1)
 */
public class SwitchDefault extends Action implements IAction {

	/**
	 * Instantiates a new switch default.
	 *
	 * @param driver the driver
	 * @param params the params
	 */
	public SwitchDefault(WebDriver driver, ActionParams params) {
		super(driver, params);
		this.type = ActionType.SwitchDefault;
		this.preExit = null;

	}

	/* (non-Javadoc)
	 * @see com.tmobile.eqre.auto.sam.action.actions.Action#mainAction()
	 */
	public void mainAction() {

		driver.switchTo().defaultContent();

	}

}
