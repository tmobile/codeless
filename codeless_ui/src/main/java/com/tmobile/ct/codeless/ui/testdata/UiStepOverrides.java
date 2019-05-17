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
package com.tmobile.ct.codeless.ui.testdata;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tmobile.ct.codeless.core.Test;
import com.tmobile.ct.codeless.core.TestDataSource;
import com.tmobile.ct.codeless.core.config.Config;
import com.tmobile.ct.codeless.core.datastructure.SourcedValue;
import com.tmobile.ct.codeless.data.BasicTestData;
import com.tmobile.ct.codeless.data.SourcedDataItem;
import com.tmobile.ct.codeless.testdata.RequestModifier;
import com.tmobile.ct.codeless.testdata.StaticTestDataSource;
import com.tmobile.ct.codeless.ui.UiStep;
import com.tmobile.ct.codeless.ui.assertion.SeleniumMethodType;
import com.tmobile.ct.codeless.ui.assertion.UiAssertionBuilder;
import com.tmobile.ct.codeless.ui.assertion.UiAssertionMethod;
import com.tmobile.ct.codeless.ui.assertion.UiSeleniumMethod;
import com.tmobile.ct.codeless.ui.build.UiTestStep;
import com.tmobile.ct.codeless.ui.modifiers.AssertionModifer;

/**
 * The Class UiStepOverrides.
 *
 * @author Sai Chandra Korpu
 */
public class UiStepOverrides {

	private static Logger log = LoggerFactory.getLogger(UiStepOverrides.class);

	public static void parseOverrides(Test test, UiTestStep uiTestStep, UiStep step) {
		List<UiAssertionBuilder> assertions = new ArrayList<>();
		List<UiStepExportBuilder> uiStepExportBuilder = new ArrayList<>();

		if (uiTestStep.getTestData() != null && uiTestStep.getTestData().size() > 0) {

			uiTestStep.getTestData().forEach(d -> {
				try {
					if (d.contains(Config.Export)) {

						String[] values = d.trim().split("::");
						if (values.length == Config.Four || values.length == Config.Five) {

							String key = values[Config.ONE];
							String methodName = values[Config.Three];
							SeleniumMethodType type = SeleniumMethodType.valueOf(values[Config.TWO]);

							String parameter = "";
							if (values.length == Config.Five)
								parameter = values[Config.Four];
							Method seleniumMethod;

							seleniumMethod = UiSeleniumMethod.getSeleniumMethod(methodName, parameter, type);
							log.info("UiStepName [" + uiTestStep.getStep() + "] Export Variable [" + key
									+ "] Selenium Method [" + seleniumMethod.toString() + "]");

							SourcedDataItem<String, TestDataSource> dataSource = createStaticTestDataSource(test, key,
									"");
							UiStepExportBuilder uiExport = new UiStepExportBuilder(seleniumMethod, parameter, type,
									dataSource);
							uiStepExportBuilder.add(uiExport);
						}
					} else if (d.contains(Config.Assert)) {
						String[] originalParts = d.trim().split("::");
						String assertionMethodName = originalParts[0];
						String seleniumMethodName = null;
						Method seleniumMethod = null;
						String parameter = "";
						String expected = "";
						SeleniumMethodType type = SeleniumMethodType.valueOf(originalParts[1]);
						if (originalParts.length > 2) {
							seleniumMethodName = originalParts[2];
						}

						if (originalParts.length >= 4) {
							expected = originalParts[3];

							if (originalParts.length == 5) {
								parameter = originalParts[4];
							}
						} else if (originalParts.length == 3) {
							if (originalParts.length == 4) {
								parameter = originalParts[3];
							}
						}

						if (seleniumMethodName != null) {
							seleniumMethod = UiSeleniumMethod.getSeleniumMethod(seleniumMethodName, parameter, type);
						}

						if (seleniumMethod != null) {
							System.out.println("expected::" +expected);
							String dataValue[] = StringUtils.substringsBetween(expected, "{{", "}}");
							ArrayList<SourcedDataItem<String, TestDataSource>> sourceValue = new ArrayList<>();
							if (dataValue != null && dataValue.length > 0) {
								for (String source: dataValue){
									sourceValue.add(test.getTestData().getSourcedValue(source));
								}
								ArrayList<TestDataSource> source = new ArrayList<>();
								if(sourceValue != null && sourceValue.size() != 0) {
									for (SourcedDataItem item : sourceValue) {
										source.add((TestDataSource) item.getValue().getValue());
									}
								}
								RequestModifier modifier = new AssertionModifer(expected,source);
								step.getRequestModifiers().add(modifier);
							}else if (dataValue[0] != null) {
								RequestModifier modifier = new AssertionModifer(dataValue[0], null);
								step.getRequestModifiers().add(modifier);
							}

							Method assertionMethod = UiAssertionMethod.getAssertionMethod(assertionMethodName,
									expected);
							log.info("UiStepName [" + uiTestStep.getStep() + "] Assertion Type ["
									+ assertionMethod.toString() + "] selenium method [" + seleniumMethod.toString()
									+ "]");

							UiAssertionBuilder assertion = new UiAssertionBuilder(assertionMethod, expected,
									seleniumMethod, type, parameter);
							assertions.add(assertion);
						}
					}
				} catch (Exception e) {
					log.error(" NoSuchMethodException " + e.getCause());

				}
			});
			step.setAssertionBuilder(assertions);
			step.setUiStepExportBuilder(uiStepExportBuilder);
		}
	}

	private static SourcedDataItem<String, TestDataSource> createStaticTestDataSource(Test test, String key,
			String key_value) {

		if (test.getTestData() == null) {
			test.setTestData(new BasicTestData());
		}

		SourcedValue<TestDataSource> value = new SourcedValue<>();
		value.setSource("Ui_Step_Overrides");
		value.setSourceClass(UiStepOverrides.class);
		value.setValue(new StaticTestDataSource(key, key_value));

		SourcedDataItem<String, TestDataSource> item = new SourcedDataItem<>(key, value);
		test.getTestData().put(key, item);

		return item;
	}

}
