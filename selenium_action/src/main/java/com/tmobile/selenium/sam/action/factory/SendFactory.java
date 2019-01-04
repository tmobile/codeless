package com.tmobile.selenium.sam.action.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.tmobile.selenium.sam.action.actions.Send;
import com.tmobile.selenium.sam.action.driver.ActionDriver;
import com.tmobile.selenium.sam.action.types.SendKeysType;
import com.tmobile.selenium.sam.action.utils.Element;
import com.tmobile.selenium.sam.config.ActionConfig;

/**
 * A factory for creating Send objects.
 *
 * @author Rob Graff
 */
public class SendFactory extends ActionFactory<SendFactory> {

	/** The element. */
	private Element element;
	
	/** The input. */
	private String input;
	
	/** The send keys type. */
	private SendKeysType sendKeysType;

	/**
	 * Instantiates a new send factory.
	 *
	 * @param driver the driver
	 * @param appConfig the app config
	 */
	public SendFactory(WebDriver driver, ActionConfig appConfig) {
		super(driver, appConfig);
		this.sendKeysType = appConfig.sendKeysType;
	}
	
	/* (non-Javadoc)
	 * @see com.tmobile.eqre.auto.sam.action.factory.ActionFactory#execute()
	 */
	public void execute(){
		Send send = new Send(driver, element, input, sendKeysType, actionParams);
		actionDriver.run(send);
	}
	
	/**
	 * Send to.
	 *
	 * @param element the element
	 * @return the send factory
	 */
	public SendFactory sendTo(WebElement element){
		this.element = new Element(element, appConfig.waitType, actionParams.waitTime);
		return this;
	}
	
	/**
	 * Text.
	 *
	 * @param text the text
	 * @return the send factory
	 */
	public SendFactory text(String text){
		this.input = text;
		return this;
	}
	
	/**
	 * Using.
	 *
	 * @param sendKeysType the send keys type
	 * @return the send factory
	 */
	public SendFactory using(SendKeysType sendKeysType){
		this.sendKeysType = sendKeysType;
		return this;
	}

}
