package com.tmobile.selenium.sam.action.driver;

/**
 * The Class ActionDriverManager.
 *
 * @author Rob Graff
 */
public class ActionDriverManager {

	/** The action drivers. */
	private static ThreadLocal<ActionDriver> actionDrivers = new ThreadLocal<ActionDriver>();
	
	/**
	 * Gets the action driver.
	 *
	 * @return the action driver
	 */
	public static ActionDriver getActionDriver(){
		if(null == actionDrivers.get()){
			setActionDriver(new ActionDriver());
		}
		return actionDrivers.get();
	}
	
	/**
	 * Sets the action driver.
	 *
	 * @param driver the new action driver
	 */
	public static void setActionDriver(ActionDriver driver){
		actionDrivers.set(driver);
	}
}
