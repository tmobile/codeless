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
package com.tmobile.ct.codeless.test.csv;

import java.util.Iterator;
import org.apache.commons.csv.CSVRecord;

import com.tmobile.ct.codeless.core.Step;
import com.tmobile.ct.codeless.core.Suite;
import com.tmobile.ct.codeless.core.Test;
import com.tmobile.ct.codeless.core.TestBuilder;
import com.tmobile.ct.codeless.core.TestData;
import com.tmobile.ct.codeless.core.datastructure.MultiValue;
import com.tmobile.ct.codeless.data.BasicTestData;
import com.tmobile.ct.codeless.service.test.build.ServiceStepBuilder;
import com.tmobile.ct.codeless.service.test.build.ServiceCallInput;
import com.tmobile.ct.codeless.test.suite.TestImpl;
import com.tmobile.ct.codeless.ui.build.UiStepBuilder;
import com.tmobile.ct.codeless.ui.build.UiStepInput;

/**
 * The Class CsvTestBuilder.
 *
 * @author Sai Chandra Korpu
 */
public class CsvTestBuilder implements TestBuilder {
	
	TestImpl test = new TestImpl();

	TestData data = new BasicTestData();

	ServiceStepBuilder serviceStepBuilder = new ServiceStepBuilder();

	UiStepBuilder uiStepBuilder = new UiStepBuilder();

	public Test build(Suite suite, Iterator<CSVRecord> rows, String name, TestData testData) {
		test.setSuite(suite);
		test.setName(name);
		test.setConfig(suite.getConfig());
		test.setTestData(testData);
		CSVRecord header = rows.next();
		while (rows.hasNext()) {
			Step step = parseRow(rows.next(), header);
			if (step != null) {
				test.addStep(step);
			}
		}

		return test;
	}

	private Step parseRow(CSVRecord row, CSVRecord header) {

		if (row.get(0) != null) {
			String stepName = row.get(0);
			if (stepName == null || stepName == "" || stepName.startsWith("#")) {
				return null;
			}
		}

		Step step = buildStep(test, row, header);
		step.setTest(test);
		return step;
	}

	private Step buildStep(Test test, CSVRecord row, CSVRecord header) {

		Iterator<String> cells = row.iterator();
		Iterator<String> headerCell = header.iterator();

		if (row.get(1).equalsIgnoreCase("SERVICECALL")) {

			ServiceCallInput input = new ServiceCallInput();
			while (cells.hasNext() && headerCell.hasNext()) {
				serviceStepBuilder.buildServiceStep(headerCell.next(), cells.next(), input, test);
			}

			return serviceStepBuilder.build(test, input);
		} else {

			UiStepInput input = new UiStepInput();
			while (cells.hasNext() && headerCell.hasNext()) {
				String headerValue = headerCell.next();
				input.add(headerValue, new MultiValue<String, String>(headerValue, cells.next()));
			}

			return uiStepBuilder.build(input, test);
		}
	}

	@Override
	public Test getTest() {
		return this.test;
	}
}
