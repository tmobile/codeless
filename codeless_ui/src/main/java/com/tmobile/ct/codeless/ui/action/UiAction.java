package com.tmobile.ct.codeless.ui.action;

import org.openqa.selenium.WebElement;

import com.tmobile.ct.codeless.core.Executable;

/**
 * The Interface UiAction.
 *
 * @author Rob Graff
 */
public interface UiAction extends Executable {

	void setText(String input);

	WebElement getElement();

}
