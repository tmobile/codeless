package com.tmobile.selenium.sam.action.driver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tmobile.selenium.sam.action.actions.Action;
import com.tmobile.selenium.sam.action.actions.IAction;
import com.tmobile.selenium.sam.action.report.StepResult;
import com.tmobile.selenium.sam.action.types.TestStatus;
import com.tmobile.selenium.sam.report.TestResult;
import com.tmobile.selenium.sam.report.TestResultManager;

/**
 * The Class ActionDriver.
 *
 * @author Rob Graff
 */
public class ActionDriver {
	
	/** The log. */
	private static Logger log = LoggerFactory.getLogger(ActionDriver.class);
	
	/** The test result. */
	private TestResult testResult = TestResultManager.getTestResult();
	
	/**
	 * Instantiates a new action driver.
	 */
	public ActionDriver(){
		
	}

	/**
	 * Run.
	 *
	 * @param action the action
	 */
	public void run(Action action) {
		try{
			action.execute();
			StepResult result = action.getStepResult();
			log.info("action[" + action.type() + "] status[" + result.getStepStatus().name() + "] time["
					+ result.getTime() + "] " + action.toString() + " screenshot[" + result.getScreenshotLocation()
					+ "]");
			testResult.addStepResult(result);
			if (!result.getStepBool() && !action.isOptional()) {
				log.info(result.getExceptionString());
				testResult.setStatus(TestStatus.fail);
				testResult.end();
				throw result.getException();
			}else if(!result.getStepBool() && action.isOptional()){
				log.info("optional action failed with exception[ "+result.getExceptionString()+" ]");
			}
		}catch(Throwable ex){
			FailedActionException fae = new FailedActionException();
			fae.initCause(ex);
			throw fae;
		}
	}
}
