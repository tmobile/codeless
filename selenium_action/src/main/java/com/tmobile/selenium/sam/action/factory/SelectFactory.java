package com.tmobile.selenium.sam.action.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.tmobile.selenium.sam.action.actions.Select;
import com.tmobile.selenium.sam.action.driver.ActionDriver;
import com.tmobile.selenium.sam.action.types.SelectType;
import com.tmobile.selenium.sam.action.utils.Element;
import com.tmobile.selenium.sam.config.ActionConfig;

/**
 * A factory for creating Select objects.
 *
 * @author Rob Graff
 */
public class SelectFactory extends ActionFactory<SelectFactory>{

	/** The element. */
	private Element element;
	
	/** The input. */
	private String input;
	
	/** The select type. */
	private SelectType selectType;
	
	/**
	 * Instantiates a new select factory.
	 *
	 * @param driver the driver
	 * @param appConfig the app config
	 */
	public SelectFactory(WebDriver driver, ActionConfig appConfig) {
		super(driver, appConfig);
		this.selectType = appConfig.selectType;
	}
	
	/* (non-Javadoc)
	 * @see com.tmobile.eqre.auto.sam.action.factory.ActionFactory#execute()
	 */
	@Override
	public void execute(){
		Select send = new Select(driver, element, input, selectType, actionParams);
		actionDriver.run(send);
	}
	
	/**
	 * Select from.
	 *
	 * @param element the element
	 * @return the select factory
	 */
	public SelectFactory selectFrom(WebElement element){
		this.element = new Element(element, appConfig.waitType, actionParams.waitTime);
		return this;
	}
	
	/**
	 * Item.
	 *
	 * @param text the text
	 * @return the select factory
	 */
	public SelectFactory item(String text){
		this.input = text;
		return this;
	}
	
	/**
	 * Using.
	 *
	 * @param selectType the select type
	 * @return the select factory
	 */
	public SelectFactory using(SelectType selectType){
		this.selectType = selectType;
		return this;
	}

}
