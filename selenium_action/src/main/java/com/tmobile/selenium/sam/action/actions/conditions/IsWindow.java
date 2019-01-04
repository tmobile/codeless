/**
 * 
 */
package com.tmobile.selenium.sam.action.actions.conditions;

import java.util.List;

import org.openqa.selenium.WebDriver;

import com.tmobile.selenium.sam.action.actions.Action;
import com.tmobile.selenium.sam.action.types.WaitType;
import com.tmobile.selenium.sam.action.utils.Wait;

/**
 * The Class IsWindow.
 *
 * @author Rob Graff (RGraff1)
 */
public class IsWindow extends Condition implements ICondition {

	/** The wait time. */
	private int waitTime;
	
	/** The wait type. */
	private WaitType waitType;

	/**
	 * Instantiates a new checks if is window.
	 *
	 * @param driver the driver
	 * @param waitType the wait type
	 * @param waitTime the wait time
	 * @param actions the actions
	 */
	public IsWindow(WebDriver driver, WaitType waitType, int waitTime, List<Action> actions) {
		super(driver);
		this.waitTime = waitTime;
		this.waitType = waitType;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.eqre.auto.sam.action.actions.conditions.Condition#check()
	 */
	public boolean check() throws Exception{
		return new Wait(driver, WaitType.window, waitTime).execute();
	}
}
