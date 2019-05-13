/*******************************************************************************
 * * Copyright 2018 T Mobile, Inc. or its affiliates. All Rights Reserved.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 *  * use this file except in compliance with the License.  You may obtain a copy
 *  * of the License at
 *  *
 *  *   http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 *  * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 *  * License for the specific language governing permissions and limitations under
 *  * the License.
 ******************************************************************************/
package com.tmobile.ct.codeless.ui.build;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.tmobile.ct.codeless.data.SourcedDataItem;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.tmobile.ct.codeless.core.Test;
import com.tmobile.ct.codeless.core.TestDataSource;
import com.tmobile.ct.codeless.core.config.Config;
import com.tmobile.ct.codeless.core.datastructure.MultiValue;
import com.tmobile.ct.codeless.testdata.RequestModifier;
import com.tmobile.ct.codeless.testdata.TestDataInput;
import com.tmobile.ct.codeless.testdata.TestDataProvider;
import com.tmobile.ct.codeless.ui.UiStep;
import com.tmobile.ct.codeless.ui.UiStepImpl;
import com.tmobile.ct.codeless.ui.action.Click;
import com.tmobile.ct.codeless.ui.action.Close;
import com.tmobile.ct.codeless.ui.action.Cookie;
import com.tmobile.ct.codeless.ui.action.Drag;
import com.tmobile.ct.codeless.ui.action.GoTo;
import com.tmobile.ct.codeless.ui.action.IFrame;
import com.tmobile.ct.codeless.ui.action.Key;
import com.tmobile.ct.codeless.ui.action.Move;
import com.tmobile.ct.codeless.ui.action.Select;
import com.tmobile.ct.codeless.ui.action.SwitchDefault;
import com.tmobile.ct.codeless.ui.action.Type;
import com.tmobile.ct.codeless.ui.action.UiAction;
import com.tmobile.ct.codeless.ui.action.Wait;
import com.tmobile.ct.codeless.ui.action.Window;
import com.tmobile.ct.codeless.ui.model.ControlElement;
import com.tmobile.ct.codeless.ui.model.controls.WebElementProxyFactory;
import com.tmobile.ct.codeless.ui.model.yaml.YamlReader;
import com.tmobile.ct.codeless.ui.modifiers.InputModifer;
import com.tmobile.ct.codeless.ui.testdata.UiStepOverrides;
import com.tmobile.selenium.sam.action.types.ClickType;
import com.tmobile.selenium.sam.action.types.MoveType;
import com.tmobile.selenium.sam.action.types.SelectType;
import com.tmobile.selenium.sam.action.types.SendKeysType;
import com.tmobile.selenium.sam.action.types.WaitType;
import com.tmobile.selenium.sam.action.types.WindowType;
import com.tmobile.selenium.sam.config.ActionConfig;
import com.tmobile.selenium.sam.config.ConfigManager;

import static com.tmobile.ct.codeless.configuration.CodelessConfiguration.getModelDir;

/**
 * The Class ExcelUiStepBuilder.
 *
 * @author Rob Graff
 */
public class UiStepBuilder {

	Test test;	

	/**
	 * Builds the.
	 *
	 * @param input the input
	 * @return the ui step
	 */
	public UiStep build(UiStepInput input, Test test) {
		
		this.test = test;
		UiStep step = new UiStepImpl();

		UiTestStep testRow = buildTestRow(input, test, step);
		testRow = parseTestData(testRow, step);
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
	private UiAction buildAction(UiTestStep testRow, ActionConfig config, UiStep step) {
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
		case "MOVE":
			action = new Move(step.getWebDriver(), config, element);
			break;
		case "KEY":
			action = new Key(step.getWebDriver(), config, element);
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
			String basePath = getModelDir() + File.separator + appName + File.separator + modelName +".yaml";
			Map<String, ControlElement> controls = YamlReader.ReadControl(basePath);
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
			case "MOVETYPE":
				config.moveType = MoveType.valueOf(value);
				break;
			case "KEYTYPE":
				config.keyType = Keys.valueOf(value);
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
	public UiTestStep buildTestRow(UiStepInput input, Test test ,UiStep step){
		UiTestStep testRow = new UiTestStep();
		input.stream().forEach(item -> {
			SuiteHeaders header = SuiteHeaders.parse(item.getKey());
			for (String value : item.getValue().getValues()) {

				if(!StringUtils.isEmpty(value)) {
					TestDataInput datainput = null;
					String[] dataValue = StringUtils.substringsBetween(value, Config.OVERRIDE_INPUT_START, Config.OVERRIDE_INPUT_END);
					if(dataValue != null && dataValue.length > 0 ) {
						datainput= new TestDataInput();
						datainput.add(item.getKey(), new MultiValue<String,TestDataProvider>(item.getKey(), new TestDataProvider(test, dataValue[0])));
						step.getTestDataInputs().add(datainput);
					}
				}

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
						if (value.contains("E+")) {
							testRow.setInput(new BigDecimal(value).toPlainString());
						} else {
							testRow.setInput(value);
						}
						break;
					case TESTDATA:
						testRow.getTestData().add(value);
						break;
					case DESCRIPTION:
						testRow.setDescription(value);
						break;
					default:
						break;
					}
				}
			}
		});
		UiStepOverrides.parseOverrides(test,testRow,step);
		return testRow;
	}

	private UiTestStep parseTestData(UiTestStep testRow, UiStep step) {

		for(TestDataInput input : step.getTestDataInputs()) {
			input.stream().forEach(key ->{
				SuiteHeaders header = SuiteHeaders.parse(key.getKey());
				TestDataProvider tdata= key.getValue().getValues().get(0);
				String value = tdata.getKey();
				TestDataSource testData = null;
				if(test.getTestData() != null) {

					testData = test.getTestData().get(value);
				}
				

				if(testData == null) {
					return;
				}

				RequestModifier modifier = null;

				switch(header){
				case TARGET:
					testRow.setTarget(testData.fullfill());
					break;
				case INPUT:
					String[] dataValue = StringUtils.substringsBetween(testRow.getInput(), "{{", "}}");
					ArrayList<SourcedDataItem<String, TestDataSource>> sourceValue = new ArrayList<>();
					if (dataValue != null && dataValue.length > 0){
						for (String source: dataValue){
							sourceValue.add(test.getTestData().getSourcedValue(source));
						}
					}
					ArrayList<TestDataSource> source = new ArrayList<>();
					if(sourceValue != null && sourceValue.size() != 0) {
						for (SourcedDataItem item : sourceValue) {
							source.add((TestDataSource) item.getValue().getValue());
						}
					}
					modifier = new InputModifer(testRow.getInput(),source); //testing
					//modifier = new InputModifer(value,testData);
					break;
				default:
					// do nothing
					break;
				}

				step.getRequestModifiers().add(modifier);

			});
		}

		return testRow;
	}

	private RequestModifier getModifer() {
		return null;
	}
}
