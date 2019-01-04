package com.tmobile.selenium.sam.action.actions;

import org.openqa.selenium.WebDriver;

import com.tmobile.selenium.sam.action.types.ActionType;

/**
 * The Class Go.
 *
 * @author Rob Graff (RGraff1)
 */
public class Go extends Action {

	/** The url. */
	private String url;

	/**
	 * Instantiates a new go.
	 *
	 * @param driver the driver
	 * @param url the url
	 * @param params the params
	 */
	public Go(WebDriver driver, String url, ActionParams params) {
		super(driver, params);
		this.type = ActionType.Go;
		this.url = url;
		this.preExit = null;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.eqre.auto.sam.action.actions.Action#mainAction()
	 */
	public void mainAction() {

		if (!driver.getWindowHandle().isEmpty()) {
			driver.manage().window().maximize();
			driver.get(url);
		}
	}

	/* (non-Javadoc)
	 * @see com.tmobile.eqre.auto.sam.action.actions.Action#toString()
	 */
	@Override
	public String toString() {
		return super.toString() + " url[" + url + "]";
	}

}
