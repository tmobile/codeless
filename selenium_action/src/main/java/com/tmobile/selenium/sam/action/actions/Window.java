package com.tmobile.selenium.sam.action.actions;

import java.util.Set;

import org.openqa.selenium.WebDriver;

import com.tmobile.selenium.sam.action.types.ActionType;
import com.tmobile.selenium.sam.action.types.WindowType;

/**
 * The Class Window.
 *
 * @author Rob Graff (RGraff1)
 */
public class Window extends Action implements IAction {

	/** The input. */
	private String input;
	
	/** The window type. */
	private WindowType windowType;

	/**
	 * Instantiates a new window.
	 *
	 * @param driver the driver
	 * @param input the input
	 * @param windowType the window type
	 * @param params the params
	 */
	public Window(WebDriver driver, String input, WindowType windowType, ActionParams params) {

		super(driver, params);
		this.type = ActionType.Window;
		this.input = input;
		this.windowType = windowType;
		this.preExit = null;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.eqre.auto.sam.action.actions.Action#mainAction()
	 */
	@Override
	public void mainAction() throws Exception {
		for(int i = 0; i < waitTime; i++){
			if((boolean) Window.class.getDeclaredMethod(windowType.name()).invoke(this)){
				break;
			}
		}

	}

	/**
	 * Title.
	 *
	 * @return true, if successful
	 */
	public boolean title() {

		Set<String> allWindows = driver.getWindowHandles();
		for (String currentWindow : allWindows) {
			if (driver.switchTo().window(currentWindow).getTitle().contains(input)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Url.
	 *
	 * @return true, if successful
	 */
	public boolean url() {
		Set<String> allWindows = driver.getWindowHandles();
		for (String currentWindow : allWindows) {
			if (driver.switchTo().window(currentWindow).getCurrentUrl().contains(input)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * First.
	 *
	 * @return true, if successful
	 */
	public boolean first(){
		
		driver.switchTo().window(driver.getWindowHandles().iterator().next());
		return true;
		
	}

	/* (non-Javadoc)
	 * @see com.tmobile.eqre.auto.sam.action.actions.Action#toString()
	 */
	@Override
	public String toString() {
		return super.toString() + " windowTitle[" + input + "]";
	}
}
