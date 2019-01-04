/**
 * 
 */
package com.tmobile.selenium.sam.action.actions;

import org.openqa.selenium.WebDriver;

import com.tmobile.selenium.sam.action.types.ActionType;

/**
 * The Class GoToStep.
 *
 * @author Rob Graff (RGraff1)
 */
public class GoToStep extends Action implements IAction {

	/** The go to step. */
	private int goToStep;

	/**
	 * Instantiates a new go to step.
	 *
	 * @param driver the driver
	 * @param goToStep the go to step
	 * @param params the params
	 */
	public GoToStep(WebDriver driver, int goToStep, ActionParams params) {
		super(driver, params);
		this.goToStep = goToStep;
		this.type = ActionType.GoToStep;
		this.preExit = null;
	}
	
	/**
	 * Gets the go to step.
	 *
	 * @return the go to step
	 */
	public int getGoToStep(){
		return goToStep;
	}
	
	/* (non-Javadoc)
	 * @see com.tmobile.eqre.auto.sam.action.actions.Action#toString()
	 */
	@Override
	public String toString() {
		return super.toString() + "goToStep["+goToStep+"]";
	}

}
