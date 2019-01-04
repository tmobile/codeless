/**
 * 
 */
package com.tmobile.selenium.sam.action.actions;

import java.lang.reflect.InvocationTargetException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import com.tmobile.selenium.sam.action.actions.conditions.IsElement;
import com.tmobile.selenium.sam.action.types.ActionType;
import com.tmobile.selenium.sam.action.types.MoveType;
import com.tmobile.selenium.sam.action.utils.Element;
import com.tmobile.selenium.sam.action.utils.Wait;

/**
 * The Class Move.
 *
 * @author Rob Graff (RGraff1)
 */
public class Move extends Action implements IAction {

	/** The element. */
	private Element element;
	
	/** The move type. */
	private MoveType moveType;

	/**
	 * Instantiates a new move.
	 *
	 * @param driver the driver
	 * @param element the element
	 * @param moveType the move type
	 * @param params the params
	 */
	public Move(WebDriver driver, Element element, MoveType moveType, ActionParams params) {
		super(driver, params);
		this.element = element;
		this.moveType = moveType;
		this.type = ActionType.Move;
		this.preExit = new IsElement(driver, element, 1);
	}
	
	/* (non-Javadoc)
	 * @see com.tmobile.eqre.auto.sam.action.actions.Action#mainAction()
	 */
	@Override
	public void mainAction() throws Exception {
		try {
			if (new Wait(driver, element, waitTime).execute()) {
				Move.class.getDeclaredMethod(moveType.name()).invoke(this);
			}
		} catch (InvocationTargetException e) {
			throw (Exception) e.getCause();
		}
	}
	
	/**
	 * Javascript.
	 */
	private void javascript(){
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element.get());
	}

	/**
	 * Action.
	 */
	private void action(){
		new Actions(driver).moveToElement(element.get()).build().perform();
	}
	
	/* (non-Javadoc)
	 * @see com.tmobile.eqre.auto.sam.action.actions.Action#toString()
	 */
	@Override
	public String toString() {
		return super.toString() + " element[" + element.locator() + "]";
	}
}
