package com.tmobile.selenium.sam.config;

import java.util.List;

import org.openqa.selenium.WebDriver;

import com.tmobile.selenium.sam.action.actions.conditions.Condition;
import com.tmobile.selenium.sam.action.report.StepResult;
import com.tmobile.selenium.sam.action.types.ActionType;
import com.tmobile.selenium.sam.action.types.ClickType;
import com.tmobile.selenium.sam.action.types.NavigateType;
import com.tmobile.selenium.sam.action.types.SelectType;
import com.tmobile.selenium.sam.action.types.SendKeysType;
import com.tmobile.selenium.sam.action.types.WaitType;
import com.tmobile.selenium.sam.action.types.WindowType;

/**
 * The Class ActionConfig.
 *
 * @author Rob Graff
 */
public class ActionConfig {
	
	/** The type. */
	public ActionType type = ActionType.Action;

	/** The driver. */
	public WebDriver driver;
	
	/** The message. */
	public String message = "";
	
	/** The wait time. */
	public int waitTime = 20;

	/** The post conditions. */
	public List<Condition> postConditions;
	
	/** The pre conditions. */
	public List<Condition> preConditions;
	
	/** The pre exit. */
	public Condition preExit;

	/** The screenshot. */
	public Boolean screenshot = false;
	
	/** The optional. */
	public Boolean optional = false;
	
	/** The wait type. */
	public WaitType waitType = WaitType.enabled;
	
	/** The click type. */
	public ClickType clickType = ClickType.javascript;
	
	/** The select type. */
	public SelectType selectType = SelectType.visibleText;
	
	/** The window type. */
	public WindowType windowType = WindowType.title;
	
	/** The send keys type. */
	public SendKeysType sendKeysType = SendKeysType.sendKeys;
	
	/** The navigate type. */
	public NavigateType navigateType = NavigateType.back;

	/** The send keys delay. */
	public long sendKeysDelay = 500;
	
	/**
	 * Instantiates a new action config.
	 */
	public ActionConfig(){}
	
	/**
	 * Instantiates a new action config.
	 *
	 * @param config the config
	 */
	public ActionConfig(ActionConfig config){
		type = config.type;
		driver = config.driver;
		message = config.message;
		waitTime = config.waitTime;
		
		postConditions = config.postConditions;
		preConditions = config.preConditions;
		preExit = config.preExit;
		
		screenshot = config.screenshot;
		optional = config.optional;
		
		waitType = config.waitType;
		clickType = config.clickType;
		selectType = config.selectType;
		windowType = config.windowType;
		sendKeysType = config.sendKeysType;
		navigateType = config.navigateType;
	}

}
