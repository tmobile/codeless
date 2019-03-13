package com.tmobile.ct.codeless.ui;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.tmobile.ct.codeless.core.Assertion;
import com.tmobile.ct.codeless.core.Config;
import com.tmobile.ct.codeless.core.Result;
import com.tmobile.ct.codeless.core.Status;
import com.tmobile.ct.codeless.core.Test;
import com.tmobile.ct.codeless.testdata.RequestModifier;
import com.tmobile.ct.codeless.testdata.TestDataInput;
import com.tmobile.ct.codeless.ui.accessor.request.AssertionModifer;
import com.tmobile.ct.codeless.ui.action.UiAction;
import com.tmobile.ct.codeless.ui.assertion.SeleniumMethodType;
import com.tmobile.ct.codeless.ui.assertion.UiAssertionBuilder;
import com.tmobile.ct.codeless.ui.driver.WebDriverFactory;
import com.tmobile.ct.codeless.ui.excel.ExcelUiTestRow;
import com.tmobile.ct.codeless.ui.model.ControlElement;

/**
 * The Class UiStepImpl.
 *
 * @author Rob Graff
 */
public class UiStepImpl implements UiStep {

	/** The name. */
	private String name;

	/** The step. */
	private ExcelUiTestRow step;

	/** The control element. */
	private ControlElement controlElement;

	/** The assertion builder. */
	private List<UiAssertionBuilder> assertionBuilder;

	/** The request modifiers. */
	private List<RequestModifier> requestModifiers = new ArrayList<>();

	/** The test. */
	private Test test;

	/** The action. */
	private UiAction action;

	/** The failure cause. */
	private Throwable failureCause;

	/** The status. */
	private Status status;

	/** The result. */
	private Result result;

	/** The retries. */
	private Integer retries = 0;

	/** The max retries. */
	private Integer maxRetries = 0;

	/** The driver. */
	private CompletableFuture<WebDriver> driver = new CompletableFuture<>();

	private List<TestDataInput> testDataInputs;

	/**
	 * Instantiates a new ui step impl.
	 */
	public UiStepImpl(){
		this.testDataInputs = new ArrayList<TestDataInput>();
	}

	/**
	 * Instantiates a new ui step impl.
	 *
	 * @param action the action
	 */
	public UiStepImpl(UiAction action){
		this.action = action;
	}


	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Executable#run()
	 */
	@Override
	public void run() {

		try {
			buildRequestModifier();
			if(test.getWebDriver() == null){
				Config config = test.getConfig();
			    WebDriverFactory.setConfig(config);
				WebDriver driver = WebDriverFactory.getWebDriver();
				test.setWebDriver(driver);
			}

			setWebDriver(test.getWebDriver());

			this.action.run();
			if (assertionBuilder != null && !assertionBuilder.isEmpty()) {
			buildAssertions(getAssertionBuilder(),action.getElement());
			}
			validate();
			status = Status.COMPLETE;
			if(result == null)
				result = Result.PASS;
			logPass();
		} catch(Exception e){
			retries = retries + 1;
			if(retries >= maxRetries){
				status = Status.COMPLETE;
				result = Result.FAIL;
				fail(e);
				logFail(e);
			    throw e;
			}
		}finally{
			markComplete();
		}
	}

	/**
	 * Builds the assertions.
	 *
	 * @param assertions the assertions
	 * @param webElement the web element
	 */
	public void buildAssertions(List<UiAssertionBuilder> assertions, WebElement webElement) {
		assertions.forEach(assertion -> {
			Method assertionMethod = assertion.getAssertMethod();
			Method seleniumMethod = assertion.getSeleniumMethod();
			Object ElementorDriver;
			if (assertion.getSeleniumMethodType() == SeleniumMethodType.WebDriver) {
				ElementorDriver = test.getWebDriver();
			} else {
				ElementorDriver = webElement;
			}
			try {
				if (assertionMethod.getParameterCount() == 1) {
					if (seleniumMethod == null) {
						assertionMethod.invoke(null, ElementorDriver);
						logStep("StepStatus.INFO", "Assertion [ " + assertionMethod + " ] ", "Succesful");
					} else if (seleniumMethod.getParameterCount() == 0) {
						assertionMethod.invoke(null, seleniumMethod.invoke(ElementorDriver));
						logStep("StepStatus.INFO", "Assertion [ " + assertionMethod + " ] ",
								"Succesful on the [ " + seleniumMethod + " ]");
					} else if (seleniumMethod.getParameterCount() == 1) {
						assertionMethod.invoke(null,
								seleniumMethod.invoke(ElementorDriver, assertion.getParameterName()));
						logStep("StepStatus.INFO", "Assertion [ " + assertionMethod + " ] ",
								"Succesful on the [ " + seleniumMethod + " ] with parameterName ");
					}
				} else if (assertionMethod.getParameterCount() == 2) {
					if (seleniumMethod.getParameterCount() == 0) {
						assertionMethod.invoke(null, seleniumMethod.invoke(ElementorDriver),
								assertion.getExpectedValue());
						logStep("StepStatus.INFO", "Assertion [ " + assertionMethod + " ] ",
								"Succesful on the [ " + seleniumMethod + " ]");
					} else if (seleniumMethod.getParameterCount() == 1) {
						assertionMethod.invoke(null,
								seleniumMethod.invoke(ElementorDriver, assertion.getParameterName()),
								assertion.getExpectedValue());
						logStep("StepStatus.INFO", "Assertion [ " + assertionMethod + " ] ",
								"Succesful on the [ " + seleniumMethod + " ] with parameterName ");
					}
				}

			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				logStep("StepStatus.FAIL", "Assertion [ " + assertionMethod + " ] ",
						" Failed on the [ " + seleniumMethod + " ]");
				try {
					status = Status.COMPLETE;
					result = Result.FAIL;
					fail(e);
					logFail(e);
					throw e;
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	/**
	 * Log step.
	 *
	 * @param status the status
	 * @param name the name
	 * @param message the message
	 */
	private void logStep(String status, String name, String message){
		test.getLogProxies().forEach(logger ->{
			logger.log(status, name, message);
		});
	}

	private void buildRequestModifier() {
		for(RequestModifier modifier : requestModifiers) {
			if(modifier != null)
				if(modifier instanceof AssertionModifer) {
					modifier.modify(assertionBuilder.get(0));
				}else {

					modifier.modify(action);
				}
		}
	}

	/**
	 * Mark complete.
	 */
	private void markComplete() {
		this.status = Status.COMPLETE;
	}


	/**
	 * Log fail.
	 *
	 * @param e the e
	 */
	private void logFail(Exception e) {
		e.printStackTrace();
	}


	/**
	 * Log pass.
	 */
	private void logPass() {
		// TODO Auto-generated method stub

	}


	/**
	 * Gets the assertion builder.
	 *
	 * @return the assertion builder
	 */
	@Override
	public List<UiAssertionBuilder> getAssertionBuilder() {
		return assertionBuilder;
	}

	/**
	 * Sets the assertion builder.
	 *
	 * @param assertionBuilder the new assertion builder
	 */
	@Override
	public void setAssertionBuilder(List<UiAssertionBuilder> assertionBuilder) {
		this.assertionBuilder = assertionBuilder;
	}

	/**
	 * Gets the control element.
	 *
	 * @return the control element
	 */
	public ControlElement getControlElement() {
		return controlElement;
	}

	/**
	 * Sets the control element.
	 *
	 * @param controlElement the new control element
	 */
	public void setControlElement(ControlElement controlElement) {
		this.controlElement = controlElement;
	}

	/**
	 * Gets the step.
	 *
	 * @return the step
	 */
	public ExcelUiTestRow getStep() {
		return step;
	}

	/**
	 * Sets the step.
	 *
	 * @param step the new step
	 */
	public void setStep(ExcelUiTestRow step) {
		this.step = step;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Validatable#getAssertions()
	 */
	@Override
	public List<Assertion> getAssertions() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Validatable#setAssertions(java.util.List)
	 */
	@Override
	public void setAssertions(List<Assertion> assertions) {
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Failable#fail(java.lang.Throwable)
	 */
	@Override
	public void fail(Throwable e) {
		this.failureCause = e;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Failable#getFailureCause()
	 */
	@Override
	public Throwable getFailureCause() {
		return failureCause;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Validatable#validate()
	 */
	@Override
	public void validate() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Step#getName()
	 */
	@Override
	public String getName() {
		return this.name;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Step#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
	this.name = name;

	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Step#getTest()
	 */
	@Override
	public Test getTest() {
		return test;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Step#setTest(com.tmobile.ct.codeless.core.Test)
	 */
	@Override
	public void setTest(Test test) {
		this.test = test;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Trackable#getResult()
	 */
	@Override
	public Result getResult() {
		return result;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Trackable#getStatus()
	 */
	@Override
	public Status getStatus() {
		return status;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Trackable#setResult(com.tmobile.ct.codeless.core.Result)
	 */
	@Override
	public void setResult(Result result) {
		this.result = result;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Trackable#setStatus(com.tmobile.ct.codeless.core.Status)
	 */
	@Override
	public void setStatus(Status status) {
		this.status = status;
	}


	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Retryable#setMaxRetries(java.lang.Integer)
	 */
	@Override
	public void setMaxRetries(Integer retries) {
		this.maxRetries = retries;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Retryable#getMaxRetries()
	 */
	@Override
	public Integer getMaxRetries() {
		return maxRetries;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.Retryable#getRetries()
	 */
	@Override
	public Integer getRetries() {
		return retries;
	}

	/**
	 * Gets the action.
	 *
	 * @return the action
	 */
	public UiAction getAction() {
		return action;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.ui.UiStep#setAction(com.tmobile.ct.codeless.ui.action.UiAction)
	 */
	@Override
	public void setAction(UiAction action) {
		this.action = action;
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.ui.UiStep#getWebDriver()
	 */
	@Override
	public Future<WebDriver> getWebDriver(){
		return driver;
	}

	/**
	 * Sets the web driver.
	 *
	 * @param driver the new web driver
	 */
	public void setWebDriver(WebDriver driver){
		this.driver.complete(driver);
	}

	@Override
	public List<TestDataInput> getTestDataInputs() {
		return testDataInputs;
	}

	public void setTestDataInputs(List<TestDataInput> testDataInputs) {
		this.testDataInputs = testDataInputs;
	}

	@Override
	public List<RequestModifier> getRequestModifiers() {
		return requestModifiers;
	}

	public void setRequestModifiers(List<RequestModifier> requestModifiers) {
		this.requestModifiers = requestModifiers;
	}



}
