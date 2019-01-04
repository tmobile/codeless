package com.tmobile.ct.codeless.ui.assertion;

import java.lang.reflect.Method;


/**
 * The Class UiAssertionBuilder.
 *
 * @author Rob Graff
 */
public class UiAssertionBuilder {
	
	/** The expected value. */
	private String expectedValue;
	
	/** The assert method. */
	private Method assertMethod;
	
	/** The selenium method. */
	private Method seleniumMethod;
	
	/** The parameter name. */
	private String parameterName;
	
	/** The selenium method type. */
	private SeleniumMethodType seleniumMethodType;

	/**
	 * Instantiates a new ui assertion builder.
	 *
	 * @param assertMethod the assert method
	 * @param expectedValue the expected value
	 * @param seleniumMethod the selenium method
	 * @param type the type
	 * @param paramaterName the paramater name
	 */
	public UiAssertionBuilder(  Method assertMethod, String expectedValue, Method seleniumMethod, SeleniumMethodType type, String paramaterName) {
		super();
		this.expectedValue = expectedValue;
		this.assertMethod = assertMethod;
		this.seleniumMethod = seleniumMethod;
		this.seleniumMethodType = type;
		this.parameterName = parameterName;
	}

	/**
	 * Gets the selenium method.
	 *
	 * @return the seleniumMethod
	 */
	public Method getSeleniumMethod() {
		return seleniumMethod;
	}

	/**
	 * Sets the selenium method.
	 *
	 * @param seleniumMethod the seleniumMethod to set
	 */
	public void setSeleniumMethod(Method seleniumMethod) {
		this.seleniumMethod = seleniumMethod;
	}

	/**
	 * Gets the parameter name.
	 *
	 * @return the parameterName
	 */
	public String getParameterName() {
		return parameterName;
	}

	/**
	 * Sets the parameter name.
	 *
	 * @param parameterName the parameterName to set
	 */
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	/**
	 * Gets the selenium method type.
	 *
	 * @return the seleniumMethodType
	 */
	public SeleniumMethodType getSeleniumMethodType() {
		return seleniumMethodType;
	}

	/**
	 * Sets the selenium method type.
	 *
	 * @param seleniumMethodType the seleniumMethodType to set
	 */
	public void setSeleniumMethodType(SeleniumMethodType seleniumMethodType) {
		this.seleniumMethodType = seleniumMethodType;
	}

	/**
	 * Gets the expected value.
	 *
	 * @return the expectedValue
	 */
	public String getExpectedValue() {
		return expectedValue;
	}

	/**
	 * Sets the expected value.
	 *
	 * @param expectedValue the expectedValue to set
	 */
	public void setExpectedValue(String expectedValue) {
		this.expectedValue = expectedValue;
	}

	/**
	 * Gets the assert method.
	 *
	 * @return the assertMethod
	 */
	public Method getAssertMethod() {
		return assertMethod;
	}

	/**
	 * Sets the assert method.
	 *
	 * @param assertMethod the assertMethod to set
	 */
	public void setAssertMethod(Method assertMethod) {
		this.assertMethod = assertMethod;
	}	

}