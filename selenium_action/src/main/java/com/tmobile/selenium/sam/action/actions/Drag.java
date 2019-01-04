/**
 * 
 */
package com.tmobile.selenium.sam.action.actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import com.tmobile.selenium.sam.action.actions.conditions.IsElement;
import com.tmobile.selenium.sam.action.types.ActionType;
import com.tmobile.selenium.sam.action.utils.Element;
import com.tmobile.selenium.sam.action.utils.Wait;

/**
 * The Class Drag.
 *
 * @author Rob Graff (RGraff1)
 */
public class Drag extends Action implements IAction {

	/** The drag. */
	private Element drag;
	
	/** The drop. */
	private Element drop;

	/**
	 * Instantiates a new drag.
	 *
	 * @param driver the driver
	 * @param drag the drag
	 * @param drop the drop
	 * @param params the params
	 */
	public Drag(WebDriver driver, Element drag, Element drop, ActionParams params) {
		super(driver, params);
		this.type = ActionType.Click;
		this.drag = drag;
		this.drop = drop;
		this.preExit = new IsElement(driver, drag, 1);
	}
	
	/* (non-Javadoc)
	 * @see com.tmobile.eqre.auto.sam.action.actions.Action#mainAction()
	 */
	@Override
	public void mainAction() throws Exception {
		if(new Wait(driver, drag, waitTime).execute() && new Wait(driver, drop, waitTime).execute()){
			new Actions(driver).dragAndDrop(drag.get(), drop.get()).build().perform();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.tmobile.eqre.auto.sam.action.actions.Action#toString()
	 */
	@Override
	public String toString() {
		return super.toString() + " drag[" + drag.locator() + "] drop[" + drop.locator() + "]";
	}

}
