package com.tmobile.ct.codeless.ui.assertion;

import java.lang.reflect.Method;

/**
 * The Class UiAssertionMethod.
 *
 * @author Rob Graff
 */
public class UiAssertionMethod {
	
	/**
	 * Gets the assertion method.
	 *
	 * @param assertionMethodName the assertion method name
	 * @param expected the expected
	 * @return the assertion method
	 * @throws NoSuchMethodException the no such method exception
	 */
	public static Method getAssertionMethod(String assertionMethodName, String expected)
			throws NoSuchMethodException {
		if (expected == "true" || expected == "false") {
			return org.testng.Assert.class.getDeclaredMethod(assertionMethodName, boolean.class);
		} else if (expected.trim().length() == 0) {
			return org.testng.Assert.class.getDeclaredMethod(assertionMethodName, Object.class);
		} else {
			return org.testng.Assert.class.getDeclaredMethod(assertionMethodName, String.class, String.class);
		}
	}

}
