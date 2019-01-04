/**
 * 
 */
package com.tmobile.selenium.sam.action.actions;

import java.util.List;

import com.tmobile.selenium.sam.action.actions.conditions.Condition;
import com.tmobile.selenium.sam.action.types.WaitType;

/**
 * The Class ActionParams.
 *
 * @author Rob Graff (RGraff1)
 */
public class ActionParams {

	/** The message. */
	public String message;
	
	/** The wait type. */
	public WaitType waitType;
	
	/** The wait time. */
	public int waitTime;
	
	/** The pre conditions. */
	public List<Condition> preConditions;
	
	/** The post conditions. */
	public List<Condition> postConditions;
	
	/** The screenshot. */
	public Boolean screenshot;
	
	/** The optional. */
	public Boolean optional;
	
	/** The step num. */
	public int stepNum;
	
	/** The send keys delay. */
	public long sendKeysDelay;

	/**
	 * Instantiates a new action params.
	 *
	 * @param message the message
	 * @param waitType the wait type
	 * @param waitTime the wait time
	 * @param preConditions the pre conditions
	 * @param postConditions the post conditions
	 * @param screenshot the screenshot
	 * @param optional the optional
	 * @param stepNum the step num
	 * @param sendKeysDelay the send keys delay
	 */
	public ActionParams(String message, WaitType waitType, int waitTime,
			List<Condition> preConditions, List<Condition> postConditions,
			Boolean screenshot, Boolean optional, int stepNum, long sendKeysDelay){
		this.message = message;
		this.waitType = waitType;
		this.waitTime = waitTime;
		
		this.preConditions = preConditions;
		this.postConditions = postConditions;
		
		this.screenshot = screenshot;
		this.optional = optional;
		this.stepNum = stepNum;
		this.sendKeysDelay = sendKeysDelay;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ActionParams [message=" + message + ", waitType=" + waitType + ", waitTime=" + waitTime
				+ ", preConditions=" + preConditions + ", postConditions=" + postConditions + ", screenshot="
				+ screenshot + ", optional=" + optional + ", stepNum=" + stepNum + "]";
	}
	
	
}
