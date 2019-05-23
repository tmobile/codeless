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

import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.tmobile.ct.codeless.configuration.CodelessConfiguration;
import com.tmobile.ct.codeless.core.Suite;
import com.tmobile.ct.codeless.core.SuiteBuilder;
import com.tmobile.ct.codeless.core.TestData;
import com.tmobile.ct.codeless.core.TestDataSource;
import com.tmobile.ct.codeless.core.datastructure.SourcedValue;
import com.tmobile.ct.codeless.data.SourcedDataItem;
import com.tmobile.ct.codeless.test.suite.SuiteImpl;
import com.tmobile.ct.codeless.test.testdata.TestDataReader;
import com.tmobile.ct.codeless.testdata.StaticTestDataSource;

/**
 * The Class ExcelSuiteBuilder.
 *
 * @author Rob Graff
 */
public class ExcelSuiteBuilder implements SuiteBuilder{

	/** The Constant SUITE_SHEET_PREFIX. */
	private static final String SUITE_SHEET_PREFIX = "S-";

	/** The Constant CONFIG_SHEET_PREFIX. */
	private static final String CONFIG_SHEET_PREFIX = "C-";

	/** The Constant TEST_SHEET_PREFIX. */
	private static final String TEST_SHEET_PREFIX = "T-";

	/** The Constant CONFIG_WAIT_TIME. */
	private static final String CONFIG_WAIT_TIME = "waitTime";

	/** The formatter. */
	private DataFormatter formatter = new DataFormatter();

	/** The suite. */
	private Suite suite;

	private TestData testData;

	/**
	 * Builds the.
	 *
	 * @param resource the resource
	 * @return the suite
	 */
	public Suite build(String resource){

		Workbook workbook = ExcelFileReader.readExcelFile(resource,true);

		suite = new SuiteImpl(resource);

		Stream<Sheet> sheets = StreamSupport
				.stream(Spliterators.spliteratorUnknownSize(workbook.sheetIterator(), Spliterator.ORDERED), false);
		sheets.forEach(this::parseConfigSheet);

		// read test data sheet before sorting / building tests
		testData = TestDataReader.getTestData(suite, testData);
		sheets = StreamSupport.stream(Spliterators.spliteratorUnknownSize(workbook.sheetIterator(), Spliterator.ORDERED), false);

		sheets.forEach(this::sortsheets);

		// default to command line, if it's not present from the command line
		// check to see if it's in the config tab of the test suite XLSX file
		String tempBuildModeKey = com.tmobile.ct.codeless.core.config.Config.BUILDMODE;
		String tempBuildModeValue = suite.getConfig().get(tempBuildModeKey);
		if (StringUtils.isNotEmpty(tempBuildModeValue) && System.getProperty(tempBuildModeKey) != null) {
			System.setProperty(tempBuildModeKey, tempBuildModeValue);
		}

		return suite;
	}

	@Override
	public Suite getSuite() {
		return null;
	}

	/**
	 * Sortsheets.
	 *
	 * @param sheet the sheet
	 */
	private void sortsheets(Sheet sheet){
		String fullname = sheet.getSheetName();
		String name = fullname.substring(2, fullname.length());
		String prefix = fullname.substring(0, 2);

		if(!sheetIsValid(prefix)){
			return;
		}

		if(prefix.toUpperCase().equalsIgnoreCase(TEST_SHEET_PREFIX)){
			suite.addTest(new ExcelTestBuilder().build(suite, sheet, name,testData)); // pass Test data to be added to test
		}else{
			//unknown sheet type, ignoring
		}
	}

	/**
	 * Sheet is valid.
	 *
	 * @param prefix the prefix
	 * @return true, if successful
	 */
	private boolean sheetIsValid(String prefix){
		if(prefix.toUpperCase().equalsIgnoreCase(CONFIG_SHEET_PREFIX)
				|| prefix.toUpperCase().equalsIgnoreCase(SUITE_SHEET_PREFIX)
				|| prefix.toUpperCase().equalsIgnoreCase(TEST_SHEET_PREFIX)){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * Parses the config sheet.
	 *
	 * @param sheet the sheet
	 * @return the config
	 */
	private void parseConfigSheet(Sheet sheet) {

		String fullname = sheet.getSheetName();
		String name = fullname.substring(2, fullname.length());
		String prefix = fullname.substring(0, 2);

		if(!sheetIsValid(prefix)){
			return;
		}

		if(!prefix.toUpperCase().equalsIgnoreCase(CONFIG_SHEET_PREFIX)) return;

		Map<String, String> config = new HashMap<>();
		boolean foundWaitTimeConfig = false;
		Properties globalproperties = CodelessConfiguration.getProperties();
		for(Row row: sheet){

			String key = formatter.formatCellValue(row.getCell(0));
			if(StringUtils.isBlank(key)){
				continue; // dont save empty rows
			}

			config.put(key, formatter.formatCellValue(row.getCell(1)));

			if (key.equals(CONFIG_WAIT_TIME)) {
				globalproperties.put(CONFIG_WAIT_TIME , formatter.formatCellValue(row.getCell(1)));
				CodelessConfiguration.setProperties(globalproperties);
				foundWaitTimeConfig=true;
			}

		}

		if (!foundWaitTimeConfig && globalproperties!=null){
			globalproperties.remove(CONFIG_WAIT_TIME);
			CodelessConfiguration.setProperties(globalproperties);
		}

		suite.setConfig(config);
	}

}
