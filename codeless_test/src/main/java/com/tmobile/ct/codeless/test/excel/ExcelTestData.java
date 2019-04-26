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
package com.tmobile.ct.codeless.test.excel;

import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.tmobile.ct.codeless.core.Suite;
import com.tmobile.ct.codeless.core.TestData;
import com.tmobile.ct.codeless.core.config.Config;
import com.tmobile.ct.codeless.test.testdata.TestDataReader;

/**
 * The Class ExcelTestData.
 *
 * @author Rob Graff
 */
public class ExcelTestData {

	private TestData testData;

	private Suite suite;

	private DataFormatter formatter = new DataFormatter();

	public ExcelTestData(Suite suite, TestData testData) {
		super();
		this.suite = suite;
		this.testData = testData;
	}

	public void parseExcelTestDataFile(String path) {
		Workbook workbook = ExcelFileReader.readExcelFile(path,true);
		Stream<Sheet> sheets = StreamSupport
				.stream(Spliterators.spliteratorUnknownSize(workbook.sheetIterator(), Spliterator.ORDERED), false);
		sheets.forEach(this::parseDefaultTestDataSheet);
		sheets = StreamSupport
				.stream(Spliterators.spliteratorUnknownSize(workbook.sheetIterator(), Spliterator.ORDERED), false);
		sheets.forEach(this::replaceTestData);
	}

	private void parseDefaultTestDataSheet(Sheet sheet) {

		String fullname = sheet.getSheetName().trim();

		if (!fullname.equalsIgnoreCase(Config.DEFAULT_TEST_DATA)) {
			return;
		}

		createTestData(sheet, Config.DEFAULT_TEST_DATA);

	}

	private void replaceTestData(Sheet sheet) {

		String fullname = sheet.getSheetName().trim();

		if (fullname.equalsIgnoreCase(Config.DEFAULT_TEST_DATA)) return;

		// check in system properties for environment specific test data sheet
		String envTestDataSheet = System.getProperty(Config.ENV_TESTDATA_SHEETNAME);

		// check in environment properties
		if(StringUtils.isBlank(envTestDataSheet)) {
			envTestDataSheet = System.getenv(Config.ENV_TESTDATA_SHEETNAME);
		}

		// check in config for test data sheet name
		if (StringUtils.isBlank(envTestDataSheet)) {
			envTestDataSheet = suite.getConfig().get(Config.TESTDATA_SHEETNAME).fullfill();
		}

		if (StringUtils.isEmpty(envTestDataSheet)) return;

		createTestData(sheet,envTestDataSheet);

	}

	private void createTestData(Sheet sheet, String source) {

		for (Row row : sheet) {

			String key = formatter.formatCellValue(row.getCell(0));
			if (StringUtils.isBlank(key))	continue;
			String key_value = formatter.formatCellValue(row.getCell(1));
			TestDataReader.createStaticTestData(key, key_value, testData, source);

		}
	}

}
