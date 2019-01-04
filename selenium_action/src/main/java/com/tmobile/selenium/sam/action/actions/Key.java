/**
 * 
 */
package com.tmobile.selenium.sam.action.actions;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import com.tmobile.selenium.sam.action.actions.conditions.IsElement;
import com.tmobile.selenium.sam.action.types.ActionType;
import com.tmobile.selenium.sam.action.utils.Element;

/**
 * The Class Key.
 *
 * @author Rob Graff (RGraff1)
 */
public class Key extends Action implements IAction{

	/** The element. */
	private Element element;
	
	/** The key. */
	private Keys key;

	/**
	 * Instantiates a new key.
	 *
	 * @param driver the driver
	 * @param element the element
	 * @param key the key
	 * @param params the params
	 */
	public Key(WebDriver driver, Element element, Keys key, ActionParams params) {
		super(driver, params);
		this.element = element;
		this.key = key;
		this.type = ActionType.Key;
		this.preExit = new IsElement(driver, element, 1);

	}
	
	/* (non-Javadoc)
	 * @see com.tmobile.eqre.auto.sam.action.actions.Action#mainAction()
	 */
	@Override
	public void mainAction() throws Exception {
		if(element != null){
			new Actions(driver).sendKeys(element.get(), key).build().perform();
		}else{
			new Actions(driver).sendKeys(key).build().perform();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.tmobile.eqre.auto.sam.action.actions.Action#toString()
	 */
	@Override
	public String toString() {
		String output = " key["+key.name()+"]";
		if(element != null){
			output = " element[" + element.locator() + "]" + output;
		}
		return super.toString() + output;
	}

}
