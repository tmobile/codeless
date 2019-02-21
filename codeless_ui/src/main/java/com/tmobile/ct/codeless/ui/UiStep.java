package com.tmobile.ct.codeless.ui;

import java.util.List;
import java.util.concurrent.Future;

import org.openqa.selenium.WebDriver;

import com.tmobile.ct.codeless.core.Retryable;
import com.tmobile.ct.codeless.core.Step;
import com.tmobile.ct.codeless.core.Trackable;
import com.tmobile.ct.codeless.testdata.TestDataInput;
import com.tmobile.ct.codeless.ui.action.UiAction;

/**
 * The Interface UiStep.
 *
 * @author Rob Graff
 */
public interface UiStep extends Step, Trackable, Retryable {

	/**
	 * Gets the web driver.
	 *
	 * @return the web driver
	 */
	Future<WebDriver> getWebDriver();

	/**
	 * Sets the action.
	 *
	 * @param action the new action
	 */
	void setAction(UiAction action);

	List<TestDataInput> getTestDataInputs();
}
