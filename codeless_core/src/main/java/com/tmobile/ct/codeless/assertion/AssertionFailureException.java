package com.tmobile.ct.codeless.assertion;

/**
 * The Class AssertionFailureException.
 *
 * @author Rob Graff
 */
public class AssertionFailureException extends RuntimeException{

	/**
	 * Instantiates a new assertion failure exception.
	 *
	 * @param message the message
	 */
	public AssertionFailureException(String message){
		super(message);
	}
}
