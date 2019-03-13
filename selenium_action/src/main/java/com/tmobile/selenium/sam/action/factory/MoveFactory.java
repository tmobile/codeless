package com.tmobile.selenium.sam.action.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.tmobile.selenium.sam.action.actions.Move;
import com.tmobile.selenium.sam.action.types.MoveType;
import com.tmobile.selenium.sam.action.utils.Element;
import com.tmobile.selenium.sam.config.ActionConfig;

public class MoveFactory extends ActionFactory<ClickFactory>{
	
	/** The element. */
	private Element element;
	
	/** The move type. */
	private MoveType moveType;


	public MoveFactory(WebDriver driver, ActionConfig appConfig) {
		super(driver, appConfig);
		this.moveType = appConfig.moveType;
		
	}
	
	@Override
	public void execute(){
		Move click = new Move(this.driver, this.element, this.moveType, this.actionParams);
		actionDriver.run(click);
	}
	
	public MoveFactory move(WebElement element){
		this.element = new Element(element, appConfig.waitType, actionParams.waitTime);
		return this;
	}

}