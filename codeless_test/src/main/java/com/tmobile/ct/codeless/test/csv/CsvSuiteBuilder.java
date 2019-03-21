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
import java.util.Arrays;
import java.util.Iterator;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import com.tmobile.ct.codeless.core.Suite;
import com.tmobile.ct.codeless.core.SuiteBuilder;
import com.tmobile.ct.codeless.core.Test;
import com.tmobile.ct.codeless.core.TestData;
import com.tmobile.ct.codeless.files.ClassPathUtil;
import com.tmobile.ct.codeless.test.suite.SuiteImpl;

/**
 * The Class CsvSuiteBuilder.
 *
 * @author Sai Chandra Korpu
 */
public class CsvSuiteBuilder implements SuiteBuilder {

	private Suite suite;

	private TestData testData;

	public Suite build(String path) {

		File folder = new File(ClassPathUtil.getAbsolutePath(path));
		suite = new SuiteImpl(folder.getPath());
		suite.setConfig(CsvConfig.getCSVProperties());
		
		Arrays.asList(folder.list()).stream().map(x -> folder.getPath() + '/' + x).forEach(resource -> {
			try {
				Test test = new CsvTestBuilder().build(suite, CSVReader(resource), getTestName(resource), testData);
				suite.addTest(test);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		return suite;
	}

	private Iterator<CSVRecord> CSVReader(String path) throws IOException {
		Reader reader = new FileReader(path);
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(reader);
		return records.iterator();
	}
	
	private String getTestName(String resource) {
		String[] parts = resource.replace(".csv",  "").split("/");
		return parts[parts.length - 1];
		
	}

	@Override
	public Suite getSuite() {
		return this.suite;
	}

}
