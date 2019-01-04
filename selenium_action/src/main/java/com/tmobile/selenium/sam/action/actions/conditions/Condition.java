package com.tmobile.selenium.sam.action.actions.conditions;

import java.util.List;

import org.openqa.selenium.WebDriver;

import com.tmobile.selenium.sam.action.actions.Action;
import com.tmobile.selenium.sam.action.driver.ListDriver;

/**
 * The Class Condition.
 *
 * @author Rob Graff (RGraff1)
 */
public class Condition implements ICondition {

	/** The driver. */
	protected WebDriver driver;
	
	/** The actions. */
	protected List<Action> actions;
	
	/**
	 * Instantiates a new condition.
	 *
	 * @param driver the driver
	 * @param actions the actions
	 */
	public Condition(WebDriver driver, List<Action> actions){
		this.driver = driver;
		this.actions = actions;
	}
	
	/**
	 * Instantiates a new condition.
	 *
	 * @param driver the driver
	 */
	public Condition(WebDriver driver){
		this.driver = driver;
		this.actions = null;
	}
	
	/* (non-Javadoc)
	 * @see com.tmobile.eqre.auto.sam.action.actions.conditions.ICondition#check()
	 */
	public boolean check() throws Exception{
		return true;
	}
	
	/* (non-Javadoc)
	 * @see com.tmobile.eqre.auto.sam.action.actions.conditions.ICondition#execute()
	 */
	public boolean execute() throws Exception{
		if(check() && hasAction()){
			System.out.println("found condition");
			new ListDriver(actions).run();
			return true;
		}
		return false;
	}
	
	/**
	 * Checks for action.
	 *
	 * @return true, if successful
	 */
	public boolean hasAction(){
		return (actions != null);
	}
}
