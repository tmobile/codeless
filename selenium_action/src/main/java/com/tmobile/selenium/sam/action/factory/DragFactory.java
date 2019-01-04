package com.tmobile.selenium.sam.action.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.tmobile.selenium.sam.action.actions.Drag;
import com.tmobile.selenium.sam.action.driver.ActionDriver;
import com.tmobile.selenium.sam.action.utils.Element;
import com.tmobile.selenium.sam.config.ActionConfig;

/**
 * A factory for creating Drag objects.
 *
 * @author Rob Graff
 */
public class DragFactory extends ActionFactory<DragFactory>{

	/** The drag. */
	private Element drag;
	
	/** The drop. */
	private Element drop;
	
	/**
	 * Instantiates a new drag factory.
	 *
	 * @param driver the driver
	 * @param appConfig the app config
	 */
	public DragFactory(WebDriver driver, ActionConfig appConfig) {
		super(driver, appConfig);
	}
	
	/* (non-Javadoc)
	 * @see com.tmobile.eqre.auto.sam.action.factory.ActionFactory#execute()
	 */
	public void execute(){
		Drag dragAction = new Drag(driver, drag, drop, actionParams);
		actionDriver.run(dragAction);
	}
	
	/**
	 * Drag.
	 *
	 * @param element the element
	 * @return the drag factory
	 */
	public DragFactory drag(WebElement element){
		this.drag = new Element(element, appConfig.waitType, actionParams.waitTime);
		return this;
	}
	
	/**
	 * To.
	 *
	 * @param element the element
	 * @return the drag factory
	 */
	public DragFactory to(WebElement element){
		this.drop = new Element(element, appConfig.waitType, actionParams.waitTime);
		return this;
	}

}
