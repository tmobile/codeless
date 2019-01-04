package com.tmobile.ct.codeless.core;

import java.lang.reflect.Method;

/**
 * The Interface Assertion.
 *
 * @author Rob Graff
 * @param <T> the generic type
 */
public interface Assertion<T> extends Executable{
	
	/**
	 * Gets the method.
	 *
	 * @return the method
	 */
	Method getMethod();
	
	/**
	 * Sets the method.
	 *
	 * @param method the new method
	 */
	void setMethod(Method method);
	
	/**
	 * Gets the method name.
	 *
	 * @return the method name
	 */
	String getMethodName();
	
	/**
	 * Sets the method name.
	 *
	 * @param name the new method name
	 */
	void setMethodName(String name);
	
	/**
	 * Gets the expected.
	 *
	 * @return the expected
	 */
	T getExpected();
	
	/**
	 * Gets the expected name.
	 *
	 * @return the expected name
	 */
	String getExpectedName();
	
	/**
	 * Gets the actual.
	 *
	 * @return the actual
	 */
	T getActual();
	
	/**
	 * Gets the actual name.
	 *
	 * @return the actual name
	 */
	String getActualName();
	
	/**
	 * Sets the expected.
	 *
	 * @param expected the new expected
	 */
	void setExpected(T expected);
	
	/**
	 * Sets the expected name.
	 *
	 * @param expected the new expected name
	 */
	void setExpectedName(String expected);
	
	/**
	 * Sets the actual.
	 *
	 * @param actual the new actual
	 */
	void setActual(T actual);
	
	/**
	 * Sets the actual name.
	 *
	 * @param actual the new actual name
	 */
	void setActualName(String actual);
	
	/**
	 * Sets the type class.
	 *
	 * @param typeClass the new type class
	 */
	void setTypeClass(Class<T> typeClass);
	
	/**
	 * Gets the type class.
	 *
	 * @return the type class
	 */
	Class<T> getTypeClass();
	
}
