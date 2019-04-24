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

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import com.tmobile.ct.codeless.core.Suite;
import com.tmobile.ct.codeless.core.TestData;
import com.tmobile.ct.codeless.core.config.Config;
import com.tmobile.ct.codeless.files.ClassPathUtil;
import com.tmobile.ct.codeless.test.testdata.TestDataReader;

/**
 * The Class CsvTestData.
 *
 * @author Sai Chandra Korpu
 */
public class CsvTestData {
	
    private TestData testData;
	
	private Suite suite;


	public CsvTestData(Suite suite, TestData testData) {
		super();
		this.suite = suite;
		this.testData = testData;
	}
	
	public void parseCsvTestDataSheet(String path) throws IOException {

		csvTestDataFileReader(path, Config.DEFAULT_TEST_DATA);
       
		// check in system properties for environment specific test data sheet
		String envTestDataSheet = System.getProperty(Config.ENV_TESTDATA_SHEETNAME);

		// check in environment properties
		if (StringUtils.isBlank(envTestDataSheet)) {
			envTestDataSheet = System.getenv(Config.ENV_TESTDATA_SHEETNAME);
		}

		// check in config for test data sheet name
		if (StringUtils.isBlank(envTestDataSheet)) {
			envTestDataSheet = suite.getConfig().get(Config.TESTDATA_SHEETNAME).fullfill();
		}
		
		if (StringUtils.isNotBlank(envTestDataSheet)) {
			path = ".." + File.separator + "testdata" + File.separator + envTestDataSheet;
			csvTestDataFileReader(path, envTestDataSheet);
		}

	}
	
	private void csvTestDataFileReader(String path, String source) throws IOException {
		
		Reader reader = new FileReader(ClassPathUtil.getAbsolutePath(path));
		Iterator<CSVRecord> records = CSVFormat.DEFAULT.parse(reader).iterator();
		while(records.hasNext()) {
			CSVRecord record = records.next();
			String key = record.get(0);
			String key_value = record.get(1);
			if (StringUtils.isBlank(key)) return;
			 TestDataReader.createStaticTestData(key, key_value, testData, source);
		}
	}
}