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

import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import com.tmobile.ct.codeless.configuration.CodelessConfiguration;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.tmobile.ct.codeless.core.Config;
import com.tmobile.ct.codeless.core.Suite;
import com.tmobile.ct.codeless.core.SuiteBuilder;
import com.tmobile.ct.codeless.core.TestData;
import com.tmobile.ct.codeless.core.TestDataSource;
import com.tmobile.ct.codeless.core.datastructure.SourcedValue;
import com.tmobile.ct.codeless.data.BasicConfig;
import com.tmobile.ct.codeless.data.BasicTestData;
import com.tmobile.ct.codeless.data.SourcedDataItem;
import com.tmobile.ct.codeless.files.ClassPathUtil;
import com.tmobile.ct.codeless.test.suite.SuiteImpl;
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

	/** The Constant TEST_DATA_PREFIX. */
	private static final String TEST_DATA_PREFIX = "D-";

	private static final String DEFAULT_TEST_DATA = "D-DATASHEET";

	private static final String SHEET_TEST_DATA = "SHEET-TEST_DATA";

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

		File workbookFile = new File(ClassPathUtil.getAbsolutePath(resource));
		Workbook workbook;
		try {
			workbook = WorkbookFactory.create(workbookFile);
		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			e.printStackTrace();
			return null;
		}

		suite = new SuiteImpl(resource);

		Stream<Sheet> sheets = StreamSupport.stream(Spliterators.spliteratorUnknownSize(workbook.sheetIterator(), Spliterator.ORDERED), false);
		// read test data sheet before sorting / building tests
		sheets.forEach(this::parseTestDataSheet);
		sheets = StreamSupport.stream(Spliterators.spliteratorUnknownSize(workbook.sheetIterator(), Spliterator.ORDERED), false);
		sheets.forEach(this::sortsheets);

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

		if(prefix.toUpperCase().equalsIgnoreCase(CONFIG_SHEET_PREFIX)){
			suite.setConfig(parseConfigSheet(sheet));
		}else if(prefix.toUpperCase().equalsIgnoreCase(TEST_SHEET_PREFIX)){
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
	private Config parseConfigSheet(Sheet sheet) {
		Config config = new BasicConfig();
		boolean foundWaitTimeConfig = false;
		Properties globalproperties = CodelessConfiguration.getProperties();
		for(Row row: sheet){

			String key = formatter.formatCellValue(row.getCell(0));
			if(StringUtils.isBlank(key)){
				continue; // dont save empty rows
			}

			StaticTestDataSource staticSource = new StaticTestDataSource(key, formatter.formatCellValue(row.getCell(1)));

			SourcedValue<TestDataSource> value = new SourcedValue<>();
			value.setSource(ExcelConfig.class.getName());
			value.setSourceClass(ExcelConfig.class);
			value.setValue(staticSource);

			SourcedDataItem<String,TestDataSource> item = new SourcedDataItem<>(key, value);
			config.put(key, item);

			if (key.toString().equals(CONFIG_WAIT_TIME)) {
				globalproperties.put(CONFIG_WAIT_TIME , formatter.formatCellValue(row.getCell(1)));
				CodelessConfiguration.setProperties(globalproperties);
				foundWaitTimeConfig=true;
			}

		}

		if (!foundWaitTimeConfig && globalproperties!=null){
			globalproperties.remove(CONFIG_WAIT_TIME);
			CodelessConfiguration.setProperties(globalproperties);
		}

		return config;
	}

	private void parseTestDataSheet(Sheet sheet) {
		// look for a specific test data sheet or load default test data
		String env = System.getProperty("dataSheet");
		String fullname = sheet.getSheetName();

		String name = fullname.substring(2, fullname.length());
		String prefix = fullname.substring(0, 2);

		if(!prefix.toUpperCase().equalsIgnoreCase(TEST_DATA_PREFIX)) {
			return;
		}

		if (!StringUtils.isEmpty(env) && !name.toUpperCase().equalsIgnoreCase(env)) {
			return;
		} else if (!fullname.toUpperCase().equalsIgnoreCase(DEFAULT_TEST_DATA)) {
			return;
		}

		testData = new BasicTestData();
		for(Row row: sheet){

			String key = formatter.formatCellValue(row.getCell(0));
			String key_value = "";
			StaticTestDataSource staticSource = null;

			if(StringUtils.isBlank(key)){
				continue; // dont save empty rows
			}

			// check test data in system properties first
			key_value = System.getProperty(key);
			if(!StringUtils.isEmpty(key_value)) {
				staticSource = new StaticTestDataSource(key,key_value);
			}

			// check test data in system environments
			if(staticSource == null) {
				key_value = System.getProperty(key);
				if(!StringUtils.isEmpty(key_value)) {
					staticSource = new StaticTestDataSource(key,key_value);
				}
			}
			if(staticSource == null) {
				staticSource = new StaticTestDataSource(key,formatter.formatCellValue(row.getCell(1)));
			}

			SourcedValue<TestDataSource> value = new SourcedValue<>();
			value.setSource(SHEET_TEST_DATA);
			value.setSourceClass(ExcelConfig.class);
			value.setValue(staticSource);

			SourcedDataItem<String,TestDataSource> item = new SourcedDataItem<>(key, value);
			testData.put(key, item);

		}
	}

}
