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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.tmobile.ct.codeless.configuration.CodelessConfiguration;
import com.tmobile.ct.codeless.core.Suite;
import com.tmobile.ct.codeless.core.SuiteBuilder;
import com.tmobile.ct.codeless.core.Test;
import com.tmobile.ct.codeless.core.TestData;
import com.tmobile.ct.codeless.test.suite.SuiteImpl;
import com.tmobile.ct.codeless.test.testdata.TestDataReader;

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
		if (StringUtils.isNotEmpty(tempBuildModeValue) && System.getProperty(tempBuildModeKey) == null) {
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
	private void sortsheets(Sheet sheet) {
		String fullname = sheet.getSheetName();
		String name = fullname.substring(2, fullname.length());
		String prefix = fullname.substring(0, 2);

		if (!sheetIsValid(prefix)) {
			return;
		}

		if (prefix.toUpperCase().equalsIgnoreCase(TEST_SHEET_PREFIX)) {
			Iterator<Row> rows = sheet.iterator();
			List<Map<String, String>> values = null;
			while (rows.hasNext()) {
				Row row = rows.next();
				if (getSafeStringFromCell(row.getCell(1)).equalsIgnoreCase("CONFIG")) {
					values = parseConfigStep(row);
				}
			}
			if (values.isEmpty()) {
				ExcelTestBuilder builder = new ExcelTestBuilder();
				Test test = builder.build(suite, sheet, name, testData);
				suite.addTest(test);
			} else {
				for (Map<String, String> config : values) {
					ExcelTestBuilder builder = new ExcelTestBuilder();
					Test test = builder.build(suite, sheet, name, testData);
					String platformType = config.get("platform-type");
					String webDriverVersion = config.get("webdriver.version.".concat(platformType));
					String osType = config.get("webdriver.platform.".concat(platformType));
					if (StringUtils.isNotBlank(platformType)) {
						test.getConfig().put("platform-type", platformType);
					} else {
						platformType = test.getConfig().get("platform-type");
					}
					if (StringUtils.isNotBlank(webDriverVersion)) {
						test.getConfig().put("webdriver.version.".concat(platformType), webDriverVersion);
					} else {
						webDriverVersion = test.getConfig().get("webdriver.version.".concat(platformType));
					}
					if (StringUtils.isNotBlank(osType)) {
						test.getConfig().put("webdriver.platform.".concat(platformType), osType);
					} else {
						osType = test.getConfig().get("webdriver.platform.".concat(platformType));
					}
					test.setName(test.getName() + " " + platformType + " " + webDriverVersion + " " + osType);
					suite.addTest(test);
				}
			}
		} else {
			// unknown sheet type, ignoring
		}
	}
	
	private String getSafeStringFromCell(Cell cell){
		return formatter.formatCellValue(cell).trim().replace(" ", "");
	}
		 
	private List<Map<String, String>> parseConfigStep(Row row) {
		List<Map<String, String>> values = new ArrayList<>();
		for (Cell cell : row) {
			String cellvalue = getSafeStringFromCell(cell);
			if (getSafeStringFromCell(cell).contains("::")) {
				if (cellvalue.contains("webdriver")) {
					for (String driverConfig : cellvalue.split(",")) {
						values.add(parseCrossBrowserConfig(driverConfig.split("::")));
					}
				}
			}
		}
		return values;
	}
	
	private Map<String, String> parseCrossBrowserConfig(String[] parts) {
		Map<String, String> values = new HashMap<String, String>();
		if (parts.length >= 2 && parts[0].equalsIgnoreCase("webdriver")) {
			String webDriver = parts[1];
			values.put("platform-type", webDriver);
			// check if version exists
			if (parts.length >= 4 && parts[2].equalsIgnoreCase("version") && StringUtils.isNotBlank(parts[3])) {
				String webDriverVersion = parts[3];
				values.put("webdriver.version." + webDriver, webDriverVersion);
				if (parts.length >= 6 && parts[4].equalsIgnoreCase("platform") && StringUtils.isNotBlank(parts[5])) {
					String osType = parts[5];
					values.put("webdriver.platform.".concat(webDriver), osType);
				}
			}
		}
		return values;
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
