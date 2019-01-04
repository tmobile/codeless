package com.tmobile.selenium.sam.action.utils;

import java.lang.reflect.InvocationTargetException;

import org.openqa.selenium.By;

/**
 * The Class Locator.
 *
 * @author Rob Graff (RGraff1)
 */
public class Locator {

	/** The locator. */
	String locator;
	
	/** The by type. */
	ByType byType;
	
	/**
	 * Instantiates a new locator.
	 *
	 * @param locator the locator
	 * @param check the check
	 */
	public Locator(String locator, ByType check){

		this.locator = locator;
		this.byType = check;
	}
	
	/**
	 * Gets the.
	 *
	 * @return the by
	 */
	public By get(){
		try {
//			System.out.println(ByType.id.name());
			return (By) By.class.getDeclaredMethod (byType.name(), String.class).invoke(null, locator);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return byType.name() + "=" + locator;
	}
	
	/**
	 * The Enum ByType.
	 *
	 * @author Rob Graff
	 */
	public enum ByType{
		
		/** The id. */
		id,
/** The name. */
name,
/** The xpath. */
xpath,
/** The css selector. */
cssSelector
	}
}
