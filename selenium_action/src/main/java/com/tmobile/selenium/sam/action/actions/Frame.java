/**
 * 
 */
package com.tmobile.selenium.sam.action.actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tmobile.selenium.sam.action.types.ActionType;
import com.tmobile.selenium.sam.action.utils.Element;

/**
 * The Class Frame.
 *
 * @author Rob Graff (RGraff1)
 */
public class Frame extends Action implements IAction {

	/** The element. */
	private Element element;

	/**
	 * Instantiates a new frame.
	 *
	 * @param driver the driver
	 * @param element the element
	 * @param params the params
	 */
	public Frame(WebDriver driver, Element element, ActionParams params) {
		super(driver, params);
		this.type = ActionType.Frame;
		this.element = element;
		this.preExit = null;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.eqre.auto.sam.action.actions.Action#mainAction()
	 */
	@Override
	public void mainAction() throws Exception{

		new WebDriverWait(driver, Duration.ofSeconds(waitTime)).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element.get()));

	}

	/* (non-Javadoc)
	 * @see com.tmobile.eqre.auto.sam.action.actions.Action#toString()
	 */
	@Override
	public String toString() {
		return super.toString() + " element[" + element.locator() + "]";
	}
}
