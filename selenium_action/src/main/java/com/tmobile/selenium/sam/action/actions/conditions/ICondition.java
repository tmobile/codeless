package com.tmobile.selenium.sam.action.actions.conditions;

/**
 * The Interface ICondition.
 *
 * @author Rob Graff
 */
public interface ICondition {

	/**
	 * Check.
	 *
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	boolean check() throws Exception;
	
	/**
	 * Execute.
	 *
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	boolean execute() throws Exception;
}
