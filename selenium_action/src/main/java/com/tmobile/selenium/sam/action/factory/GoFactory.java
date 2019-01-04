package com.tmobile.selenium.sam.action.factory;

import org.openqa.selenium.WebDriver;

import com.tmobile.selenium.sam.action.actions.Go;
import com.tmobile.selenium.sam.action.driver.ActionDriver;
import com.tmobile.selenium.sam.config.ActionConfig;

/**
 * A factory for creating Go objects.
 *
 * @author Rob Graff
 */
public class GoFactory extends ActionFactory<GoFactory> {
	
	/** The url. */
	private String url;

	/**
	 * Instantiates a new go factory.
	 *
	 * @param driver the driver
	 * @param appConfig the app config
	 */
	public GoFactory(WebDriver driver, ActionConfig appConfig) {
		super(driver, appConfig);
	}
	
	/* (non-Javadoc)
	 * @see com.tmobile.eqre.auto.sam.action.factory.ActionFactory#execute()
	 */
	@Override
	public void execute(){
		Go go = new Go(this.driver, this.url, this.actionParams);
		actionDriver.run(go);
	}
	
	/**
	 * Go.
	 *
	 * @param url the url
	 * @return the go factory
	 */
	public GoFactory go(String url){
		this.url = url;
		return this;
	}

}
