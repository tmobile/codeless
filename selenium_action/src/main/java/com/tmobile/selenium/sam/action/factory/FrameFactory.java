package com.tmobile.selenium.sam.action.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.tmobile.selenium.sam.action.actions.Frame;
import com.tmobile.selenium.sam.action.driver.ActionDriver;
import com.tmobile.selenium.sam.action.utils.Element;
import com.tmobile.selenium.sam.config.ActionConfig;

/**
 * A factory for creating Frame objects.
 *
 * @author Rob Graff
 */
public class FrameFactory extends ActionFactory<FrameFactory>{

	/** The element. */
	private Element element;

	/**
	 * Instantiates a new frame factory.
	 *
	 * @param driver the driver
	 * @param appConfig the app config
	 */
	public FrameFactory(WebDriver driver, ActionConfig appConfig) {
		super(driver, appConfig);
	}
	
	/* (non-Javadoc)
	 * @see com.tmobile.eqre.auto.sam.action.factory.ActionFactory#execute()
	 */
	@Override
	public void execute(){
		Frame frame = new Frame(driver, element, actionParams);
		actionDriver.run(frame);
	}
	
	/**
	 * Frame.
	 *
	 * @param element the element
	 * @return the frame factory
	 */
	public FrameFactory frame(WebElement element){
		this.element = new Element(element, appConfig.waitType, actionParams.waitTime);
		return this;
	}
	

}
