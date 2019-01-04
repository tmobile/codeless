package com.tmobile.selenium.sam.action.factory;

import org.openqa.selenium.WebDriver;

import com.tmobile.selenium.sam.action.actions.SwitchDefault;
import com.tmobile.selenium.sam.config.ActionConfig;

/**
 * A factory for creating SwitchDefault objects.
 *
 * @author Rob Graff
 */
public class SwitchDefaultFactory extends ActionFactory<SwitchDefaultFactory> {

	/**
	 * Instantiates a new switch default factory.
	 *
	 * @param driver the driver
	 * @param appConfig the app config
	 */
	public SwitchDefaultFactory(WebDriver driver, ActionConfig appConfig) {
		super(driver, appConfig);
	}
	
	/* (non-Javadoc)
	 * @see com.tmobile.eqre.auto.sam.action.factory.ActionFactory#execute()
	 */
	@Override
	public void execute(){
		SwitchDefault switchDefault = new SwitchDefault(this.driver, this.actionParams);
		actionDriver.run(switchDefault);
	}
	
	/**
	 * Switch default.
	 *
	 * @return the switch default factory
	 */
	public SwitchDefaultFactory switchDefault(){
		return this;
	}

}
