/**
 * 
 */
package com.tmobile.selenium.sam.action.actions.conditions;

import java.util.List;

import org.openqa.selenium.WebDriver;

import com.tmobile.selenium.sam.action.actions.Action;
import com.tmobile.selenium.sam.action.utils.Element;
import com.tmobile.selenium.sam.action.utils.Wait;

/**
 * The Class IsElementInFrame.
 *
 * @author Rob Graff (RGraff1)
 */
public class IsElementInFrame extends Condition implements ICondition {

	/** The element. */
	private Element element;
	
	/** The wait time. */
	private int waitTime;
	
	/** The i frames. */
	private List<Element> iFrames;

	/**
	 * Instantiates a new checks if is element in frame.
	 *
	 * @param driver the driver
	 * @param element the element
	 * @param waitTime the wait time
	 * @param iFrames the i frames
	 * @param actions the actions
	 */
	public IsElementInFrame(WebDriver driver, Element element, int waitTime, List<Element> iFrames, List<Action> actions) {
		super(driver, actions);
		this.element = element;
		this.waitTime = waitTime;
		this.iFrames = iFrames;
	}

	/**
	 * Instantiates a new checks if is element in frame.
	 *
	 * @param driver the driver
	 * @param element the element
	 * @param iFrames the i frames
	 * @param actions the actions
	 */
	public IsElementInFrame(WebDriver driver, Element element, List<Element> iFrames, List<Action> actions) {
		super(driver, actions);
		this.element = element;
		this.waitTime = element.getWaitTime();
		this.iFrames = iFrames;
	}

	/**
	 * Instantiates a new checks if is element in frame.
	 *
	 * @param driver the driver
	 * @param element the element
	 * @param iFrames the i frames
	 * @param waitTime the wait time
	 */
	public IsElementInFrame(WebDriver driver, Element element, List<Element> iFrames, int waitTime) {
		super(driver);
		this.element = element;
		this.waitTime = waitTime;
		this.iFrames = iFrames;
	}

	/**
	 * Instantiates a new checks if is element in frame.
	 *
	 * @param driver the driver
	 * @param element the element
	 * @param iFrames the i frames
	 */
	public IsElementInFrame(WebDriver driver, Element element, List<Element> iFrames) {
		super(driver);
		this.element = element;
		this.waitTime = element.getWaitTime();
		this.iFrames = iFrames;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.eqre.auto.sam.action.actions.conditions.Condition#check()
	 */
	@Override
	public boolean check() {
		try {
			for (Element frame : iFrames) {
				if (!new Wait(driver, frame, waitTime).execute()){
					driver.switchTo().defaultContent();
					return false;
				}
			}
			if (new Wait(driver, element, waitTime).execute()) {
				return true;
			} else {
				driver.switchTo().defaultContent();
				return false;
			}
		} catch (Exception e) {
			driver.switchTo().defaultContent();
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see com.tmobile.eqre.auto.sam.action.actions.conditions.Condition#execute()
	 */
	@Override
	public boolean execute() throws Exception {
		boolean status = super.execute();
		driver.switchTo().defaultContent();
		return status;
	}

}
