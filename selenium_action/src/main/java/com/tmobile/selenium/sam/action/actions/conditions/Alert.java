/**
 * 
 */
package com.tmobile.selenium.sam.action.actions.conditions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * The Class Alert.
 *
 * @author Rob Graff (RGraff1)
 */
public class Alert extends Condition implements ICondition {

	/** The wait time. */
	private int waitTime;

	/**
	 * Instantiates a new alert.
	 *
	 * @param driver the driver
	 * @param waitTime the wait time
	 */
	public Alert(WebDriver driver, int waitTime) {
		super(driver);
		this.waitTime = waitTime;

	}

	/* (non-Javadoc)
	 * @see com.tmobile.eqre.auto.sam.action.actions.conditions.Condition#check()
	 */
	@Override
	public boolean check() {
		try {
			new WebDriverWait(driver, Duration.ofSeconds(waitTime)).until(ExpectedConditions.alertIsPresent()).accept();
			return true;
		} catch (Exception e) {
			return false;
		}

	}
	
	/* (non-Javadoc)
	 * @see com.tmobile.eqre.auto.sam.action.actions.conditions.Condition#execute()
	 */
	@Override
	public boolean execute(){
		return check();
	}

}
