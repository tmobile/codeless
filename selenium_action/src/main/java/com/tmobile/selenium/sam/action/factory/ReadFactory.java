package com.tmobile.selenium.sam.action.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.tmobile.selenium.sam.action.actions.Read;
import com.tmobile.selenium.sam.action.driver.ActionDriver;
import com.tmobile.selenium.sam.action.utils.Element;
import com.tmobile.selenium.sam.config.ActionConfig;

/**
 * A factory for creating Read objects.
 *
 * @author Rob Graff
 */
public class ReadFactory extends ActionFactory<ReadFactory>{

	/** The element. */
	private Element element;
	
	/** The output. */
	private String output;
	
	/** The read. */
	Read read;

	/**
	 * Instantiates a new read factory.
	 *
	 * @param driver the driver
	 * @param appConfig the app config
	 */
	public ReadFactory(WebDriver driver, ActionConfig appConfig) {
		super(driver, appConfig);
	}
	
	/* (non-Javadoc)
	 * @see com.tmobile.eqre.auto.sam.action.factory.ActionFactory#execute()
	 */
	@Override
	public void execute(){
		read = new Read(this.driver, this.element, this.output, this.actionParams);
		actionDriver.run(read);
	}
	
	/**
	 * Read.
	 *
	 * @param element the element
	 * @return the read factory
	 */
	public ReadFactory read(WebElement element){
		this.element = new Element(element, appConfig.waitType, actionParams.waitTime);
		return this;
	}

	/**
	 * Return value.
	 *
	 * @return the string
	 */
	public String returnValue(){
		execute();
		return read.returnValue();
	}

}
