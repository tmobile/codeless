package com.tmobile.selenium.sam.action.actions;

import java.lang.reflect.InvocationTargetException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import com.tmobile.selenium.sam.action.actions.conditions.IsElement;
import com.tmobile.selenium.sam.action.types.ActionType;
import com.tmobile.selenium.sam.action.types.ClickType;
import com.tmobile.selenium.sam.action.utils.Element;
import com.tmobile.selenium.sam.action.utils.Wait;

/**
 * The Class Click.
 *
 * @author Rob Graff (RGraff1)
 */
public class Click extends Action implements IAction {

	/** The element. */
	private Element element;
	
	/** The click type. */
	private ClickType clickType;

	/**
	 * Instantiates a new click.
	 *
	 * @param driver the driver
	 * @param element the element
	 * @param clickType the click type
	 * @param params the params
	 */
	public Click(WebDriver driver, Element element, ClickType clickType, ActionParams params) {

		super(driver, params);
		this.type = ActionType.Click;
		this.element = element;
		this.clickType = clickType;
		this.preExit = new IsElement(driver, element, 1);
	}

	/* (non-Javadoc)
	 * @see com.tmobile.eqre.auto.sam.action.actions.Action#mainAction()
	 */
	@Override
	public void mainAction() throws Exception {
		try {
			if (new Wait(driver, element, waitTime).execute()) {
				Click.class.getDeclaredMethod(clickType.name()).invoke(this);
			}
		} catch (InvocationTargetException e) {
			throw (Exception) e.getCause();
		}
	}

	/**
	 * Click.
	 */
	private void click() {
		element.get().click();
	}

	/**
	 * Javascript.
	 */
	private void javascript() {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element.get());
	}
	
	/**
	 * scrollIntoView.
	 */
	private void scrollIntoView() {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element.get());
	}
	
	/**
	 * scrollDown.
	 */
	private void scrollDown() {

		new Actions(driver).moveToElement(element.get()).click().sendKeys(Keys.ARROW_DOWN).build().perform();
	}
	
	/**
	 * scrollUp.
	 */
	private void scrollUp() {

		new Actions(driver).moveToElement(element.get()).click().sendKeys(Keys.ARROW_UP).build().perform();
	}

	/**
	 * Send enter.
	 */
	private void sendEnter() {
		element.get().sendKeys(Keys.ENTER);
	}
	
	/**
	 * Action.
	 */
	private void action(){
		new Actions(driver).click(element.get()).build().perform();
	}

	/* (non-Javadoc)
	 * @see com.tmobile.eqre.auto.sam.action.actions.Action#toString()
	 */
	@Override
	public String toString() {
		return super.toString() + " clickType["+clickType.name()+"] element[" + element.locator() + "]";
	}

}
