package com.tmobile.ct.codeless.ui.excel;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.WebElement;

import com.tmobile.ct.codeless.core.Step;
import com.tmobile.ct.codeless.core.TestData;
import com.tmobile.ct.codeless.core.datastructure.MultiValue;
import com.tmobile.ct.codeless.ui.UiStep;
import com.tmobile.ct.codeless.ui.UiStepImpl;
import com.tmobile.ct.codeless.ui.action.Click;
import com.tmobile.ct.codeless.ui.action.Close;
import com.tmobile.ct.codeless.ui.action.Cookie;
import com.tmobile.ct.codeless.ui.action.Drag;
import com.tmobile.ct.codeless.ui.action.GoTo;
import com.tmobile.ct.codeless.ui.action.IFrame;
import com.tmobile.ct.codeless.ui.action.Select;
import com.tmobile.ct.codeless.ui.action.SwitchDefault;
import com.tmobile.ct.codeless.ui.action.Type;
import com.tmobile.ct.codeless.ui.action.UiAction;
import com.tmobile.ct.codeless.ui.action.Wait;
import com.tmobile.ct.codeless.ui.action.Window;
import com.tmobile.ct.codeless.ui.model.ControlElement;
import com.tmobile.ct.codeless.ui.model.controls.WebElementProxyFactory;
import com.tmobile.ct.codeless.ui.model.yaml.YamlReader;
import com.tmobile.selenium.sam.action.types.ClickType;
import com.tmobile.selenium.sam.action.types.SelectType;
import com.tmobile.selenium.sam.action.types.SendKeysType;
import com.tmobile.selenium.sam.action.types.WaitType;
import com.tmobile.selenium.sam.action.types.WindowType;
import com.tmobile.selenium.sam.config.ActionConfig;
import com.tmobile.selenium.sam.config.ConfigManager;

/**
 * The Class ExcelUiStepBuilder.
 *
 * @author Rob Graff
 */
public class ExcelUiStepBuilder {

	/** The formatter. */
	private DataFormatter formatter = new DataFormatter();

	private static String OVERRIDE_INPUT = "$VAR=";

	/**
	 * Builds the.
	 *
	 * @param row the row
	 * @return the step
	 */
	public Step build(Row row, TestData testData){
		UiStepInput input = new UiStepInput();
		for(Cell cell : row){
			String header = formatter.formatCellValue(cell.getSheet().getRow(0).getCell(cell.getColumnIndex())).trim().toUpperCase();
			String value = formatter.formatCellValue(cell);
			input.add(header, new MultiValue<String,String>(header, value));
		}
		return build(input,testData);
	}

	/**
	 * Builds the.
	 *
	 * @param input the input
	 * @return the ui step
	 */
	public UiStep build(UiStepInput input, TestData testData) {
		UiStep step = new UiStepImpl();

		ExcelUiTestRow testRow = buildTestRow(input, testData);
		step.setName(testRow.getStep());

		ActionConfig config = buildConfig(testRow.getTestData());
		config.message = step.getName();

		UiAction action = buildAction(testRow, config, step);
		step.setAction(action);

		return step;
	}

	/**
	 * Builds the action.
	 *
	 * @param testRow the test row
	 * @param config the config
	 * @param step the step
	 * @return the ui action
	 */
	private UiAction buildAction(ExcelUiTestRow testRow, ActionConfig config, UiStep step) {
		String actionType = testRow.getAction();
		if(StringUtils.isBlank(actionType)){
			return null;
		}
		UiAction action = null;
		actionType = actionType.trim().toUpperCase();

		WebElement element = buildTargetElement(testRow.getTarget(), step);
		String input = Optional.ofNullable(testRow.getInput()).map(String::trim).orElse("");

		switch(actionType){
		case "CLICK":
			action = new Click(step.getWebDriver(), config, element);
			break;
		case "TYPE":
			action = new Type(step.getWebDriver(), config, element, input);
			break;
		case "GOTO":
			action = new GoTo(step.getWebDriver(), config, element, input);
			break;
		case "SELECT":
			action = new Select(step.getWebDriver(), config, element, input);
			break;
		case "COOKIE":
			action = new Cookie(step.getWebDriver(), config, element, input.split("=")[0], input.split("=")[1]);
			break;
		case "DRAG":
			action = new Drag(step.getWebDriver(), config, element, buildTargetElement(input, step));
			break;
		case "IFRAME":
			action = new IFrame(step.getWebDriver(), config, element);
			break;
		case "SWITCHDEFAULT":
			action = new SwitchDefault(step.getWebDriver(), config, element);
			break;
		case "WAIT":
			action = new Wait(step.getWebDriver(), config, element);
			break;
		case "WINDOW":
			action = new Window(step.getWebDriver(), config, element, input);
			break;
		case "SENDKEYS":
			action = new Type(step.getWebDriver(), config, element, input);
			break;
		case "CLOSE":
			action = new Close(step.getWebDriver(), config, element);
			break;
		}
		return action;
	}

	/**
	 * Builds the target element.
	 *
	 * @param target the target
	 * @param step the step
	 * @return the web element
	 */
	private WebElement buildTargetElement(String target, UiStep step) {

		if (StringUtils.isBlank(target)) {
			return null;
		}

		String[] parts = target.trim().split("[.]");
		if (parts.length == 3) {
			String appName = parts[0];
			String modelName = parts[1];
			String targetName = parts[2];
			Map<String, ControlElement> controls = YamlReader.ReadControl(appName, modelName);
			if(controls != null && controls.containsKey(targetName)){
				ControlElement control = controls.get(targetName);
				return new WebElementProxyFactory().fromControlElement(step.getWebDriver(), control);
			}
		}

		return null;
	}

	/**
	 * Builds the config.
	 *
	 * @param testData the test data
	 * @return the action config
	 */
	public ActionConfig buildConfig(List<String> testData){

		ActionConfig config = new ActionConfig(ConfigManager.getActionConfig());
		for(String item : testData){

			if(!item.contains("::")){
				continue;
			}

			String[] parts = item.split("::");
			String key = parts[0];
			String value = parts[1];

			if(StringUtils.isBlank(key) || StringUtils.isBlank(value)){
				continue;
			}

			key = key.toUpperCase().trim();
			value = value.trim();

			switch(key){
			case "WAITTIME":
				config.waitTime = Integer.valueOf(value);
				break;
			case "WAITTYPE":
				config.waitType = WaitType.valueOf(value);
				break;
			case "SCREENSHOT":
				config.screenshot = Boolean.valueOf(value);
				break;
			case "OPTIONAL":
				config.optional = Boolean.valueOf(value);
				break;
			case "CLICKTYPE":
				config.clickType = ClickType.valueOf(value);
				break;
			case "SELECTTYPE":
				config.selectType = SelectType.valueOf(value);
				break;
			case "WINDOWTYPE":
				config.windowType = WindowType.valueOf(value);
				break;
			case "SENDKEYSTYPE":
				config.sendKeysType = SendKeysType.valueOf(value);
				break;
			case "SENDKEYSDELAY":
				config.sendKeysDelay = Long.valueOf(value);
				break;
			case "MESSAGE":
				config.message = value;
				break;
			}
		}
		return config;
	}

	/**
	 * Builds the test row.
	 *
	 * @param input the input
	 * @return the excel ui test row
	 */
	public ExcelUiTestRow buildTestRow(UiStepInput input, TestData testData){
		ExcelUiTestRow testRow = new ExcelUiTestRow();
		input.stream().forEach(item -> {
			SuiteHeaders header = SuiteHeaders.parse(item.getKey());
			for (String value : item.getValue().getValues()) {
				if (value != null && value.trim().length() > 0) {
					switch (header) {
					case STEP:
						testRow.setStep(value);
						break;
					case ACTION:
						testRow.setAction(value);
						break;
					case TARGET:
						testRow.setTarget(value);
						break;
					case INPUT:
						String dynamicValue = overrideTestInput(value,testData);
						if(!StringUtils.isEmpty(dynamicValue)) {
							value = dynamicValue;
						}
						testRow.setInput(value);
						break;
					case TESTDATA:
						testRow.getTestData().add(value);
						break;
					default:
						break;
					}
				}
			}
		});
		return testRow;
	}

	private String overrideTestInput(String value, TestData testData) {
		if(value.length() < 5) { // should be at least length of $var= string
			return null;
		}
		String prefix = value.substring(0, 5);

		if(OVERRIDE_INPUT.toUpperCase().equalsIgnoreCase(prefix) && testData != null) {
			String postfix = value.substring(5,value.length());
			// check test data in system properties first
			String sys_value = System.getProperty(postfix);
			if(!StringUtils.isEmpty(sys_value)) {
				return sys_value;
			}

			// check test data in system environments
			String sysEnv = System.getenv(postfix);
			if(!StringUtils.isEmpty(sysEnv)) {
				return sysEnv;
			}

			// check test data for override value
			if(testData.asMap().containsKey(postfix)) {
				String overrideValue = testData.get(postfix);
				if(!StringUtils.isEmpty(overrideValue.trim())) {
					return overrideValue;
				}
			}
		}
		return null;
	}
}
