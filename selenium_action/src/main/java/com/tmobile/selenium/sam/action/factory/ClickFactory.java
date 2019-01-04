package com.tmobile.selenium.sam.action.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.tmobile.selenium.sam.action.actions.Click;
import com.tmobile.selenium.sam.action.driver.ActionDriver;
import com.tmobile.selenium.sam.action.types.ClickType;
import com.tmobile.selenium.sam.action.utils.Element;
import com.tmobile.selenium.sam.config.ActionConfig;

/**
 * A factory for creating Click objects.
 *
 * @author Rob Graff
 */
public class ClickFactory extends ActionFactory<ClickFactory>{
	
	/** The element. */
	private Element element;
	
	/** The click type. */
	private ClickType clickType;

	/**
	 * Instantiates a new click factory.
	 *
	 * @param driver the driver
	 * @param appConfig the app config
	 */
	public ClickFactory(WebDriver driver, ActionConfig appConfig) {
		super(driver, appConfig);
		this.clickType = appConfig.clickType;
		
	}
	
	/* (non-Javadoc)
	 * @see com.tmobile.eqre.auto.sam.action.factory.ActionFactory#execute()
	 */
	@Override
	public void execute(){
		Click click = new Click(this.driver, this.element, this.clickType, this.actionParams);
		actionDriver.run(click);
	}
	
	/**
	 * Click.
	 *
	 * @param element the element
	 * @return the click factory
	 */
	public ClickFactory click(WebElement element){
		this.element = new Element(element, appConfig.waitType, actionParams.waitTime);
		return this;
	}
	
	/**
	 * Using.
	 *
	 * @param clickType the click type
	 * @return the click factory
	 */
	public ClickFactory using(ClickType clickType){
		this.clickType = clickType;
		return this;
	}

}
