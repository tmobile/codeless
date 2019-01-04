package com.tmobile.ct.codeless.ui.model;

/**
 * The Class ControlElement.
 *
 * @author Rob Graff
 */
public class ControlElement {
	
	/** The by. */
	private String by;
	
	/** The locator. */
	private String locator;

	/**
	 * Instantiates a new control element.
	 */
	public ControlElement(){}
	
	/**
	 * Instantiates a new control element.
	 *
	 * @param by the by
	 * @param locator the locator
	 */
	public ControlElement(String by, String locator) {
		this.by = by;
		this.locator = locator;
	}

	/**
	 * Gets the locator.
	 *
	 * @return the locatorValue
	 */
	public String getLocator() {
		return locator;
	}
	
	/**
	 * Sets the locator.
	 *
	 * @param locator the new locator
	 */
	public void setLocator(String locator) {
		this.locator = locator;
	}
	
	/**
	 * Gets the by.
	 *
	 * @return the By type
	 */
	public String getBy() {
		return by;
	}
	
	/**
	 * Sets the by.
	 *
	 * @param by the new by
	 */
	public void setBy(String by) {
		this.by = by;
	}
}
