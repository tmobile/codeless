package com.tmobile.selenium.sam.action.actions;

import java.lang.reflect.InvocationTargetException;

import org.openqa.selenium.WebDriver;

import com.tmobile.selenium.sam.action.actions.conditions.IsElement;
import com.tmobile.selenium.sam.action.types.ActionType;
import com.tmobile.selenium.sam.action.types.SelectType;
import com.tmobile.selenium.sam.action.utils.Element;
import com.tmobile.selenium.sam.action.utils.Wait;

/**
 * The Class Select.
 *
 * @author Rob Graff (RGraff1)
 */
public class Select extends Action implements IAction {

	/** The element. */
	private Element element;
	
	/** The input. */
	private String input;
	
	/** The select type. */
	private SelectType selectType;

	/**
	 * Instantiates a new select.
	 *
	 * @param driver the driver
	 * @param element the element
	 * @param input the input
	 * @param selectType the select type
	 * @param params the params
	 */
	public Select(WebDriver driver, Element element, String input, SelectType selectType, ActionParams params) {
		super(driver, params);
		this.type = ActionType.Select;
		this.element = element;
		this.input = input;
		this.selectType = selectType;
		this.preExit = new IsElement(driver, element, 1);
		
	}
	
	/* (non-Javadoc)
	 * @see com.tmobile.eqre.auto.sam.action.actions.Action#mainAction()
	 */
	@Override
	public void mainAction() throws Exception {
		try {
			if (new Wait(driver, element, waitTime).execute()) {
				Select.class.getDeclaredMethod(selectType.name()).invoke(this);
			}
		} catch (InvocationTargetException e) {
			throw (Exception) e.getCause();
		}
	}
	
	/**
	 * Visible text.
	 */
	private void visibleText(){
		new org.openqa.selenium.support.ui.Select(element.get()).selectByVisibleText(input);
	}
	
	/**
	 * Value.
	 */
	private void value(){
		new org.openqa.selenium.support.ui.Select(element.get()).selectByValue(input);
	}

	/* (non-Javadoc)
	 * @see com.tmobile.eqre.auto.sam.action.actions.Action#toString()
	 */
	@Override
	public String toString() {
		return super.toString() + " element[" + element.locator() + "] value["+input+"] selectBy["+selectType.name()+"]";
	}
}
