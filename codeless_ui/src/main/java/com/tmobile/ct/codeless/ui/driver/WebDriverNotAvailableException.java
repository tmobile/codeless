package com.tmobile.ct.codeless.ui.driver;

/**
 * The Class WebDriverNotAvailableException.
 *
 * @author Rob Graff
 */
public class WebDriverNotAvailableException extends RuntimeException{

	/**
	 * Instantiates a new web driver not available exception.
	 *
	 * @param message the message
	 */
	public WebDriverNotAvailableException(String message){
		super(message);
	}
}
