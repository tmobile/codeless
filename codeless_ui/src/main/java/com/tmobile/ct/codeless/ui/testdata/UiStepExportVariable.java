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
import java.util.List;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tmobile.ct.codeless.core.Test;
import com.tmobile.ct.codeless.core.TestDataSource;
import com.tmobile.ct.codeless.data.SourcedDataItem;
import com.tmobile.ct.codeless.testdata.StaticTestDataSource;
import com.tmobile.ct.codeless.ui.assertion.SeleniumMethodType;

/**
 * The Class UiStepExportVariable.
 *
 * @author Sai Chandra Korpu
 */
public class UiStepExportVariable {
	
	private static Logger log = LoggerFactory.getLogger(UiStepExportVariable.class);

	public static void buildExport(Test test, List<UiStepExportBuilder> exportBuilder, WebElement webElement) {

		exportBuilder.forEach(export -> {
			Method seleniumMethod = export.getSeleniumMethod();
			Object ElementorDriver = export.getSeleniumMethodType() == SeleniumMethodType.WebDriver
					? test.getWebDriver()
					: webElement;
			try {
				Object value = seleniumMethod.getParameterCount() == 0 ? seleniumMethod.invoke(ElementorDriver)
						: seleniumMethod.invoke(ElementorDriver, export.getParameterName());
				
				SourcedDataItem<String, TestDataSource> sourcedData = export.getTestDataSource();
				log.info("Ui Step Exported Key ["+sourcedData.getKey()+"] value ["+value.toString()+"]");
				
				if (value != null && sourcedData.getValue().getValue() instanceof StaticTestDataSource) {
					StaticTestDataSource staticSource = (StaticTestDataSource) sourcedData.getValue().getValue();
					staticSource.setValue(value.toString());
				}
			} catch (Exception e) {

				throw new RuntimeException("UI Step EXPORT Variable Failed " + e.getCause());
			}
		});

	}
}
