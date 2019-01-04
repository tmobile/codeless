package com.tmobile.selenium.sam.action.actions;

import com.tmobile.selenium.sam.action.types.ActionType;

/**
 * The Interface IAction.
 *
 * @author Rob Graff
 */
public interface IAction {
	
	/**
	 * Type.
	 *
	 * @return the action type
	 */
	ActionType type();
	
	/**
	 * Pre.
	 *
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	boolean pre() throws Exception;
	
	/**
	 * Post.
	 *
	 * @return true, if successful
	 */
	boolean post();
	
//	boolean execute();
	
	/**
 * Main action.
 *
 * @throws Exception the exception
 */
void mainAction() throws Exception;
	
}
