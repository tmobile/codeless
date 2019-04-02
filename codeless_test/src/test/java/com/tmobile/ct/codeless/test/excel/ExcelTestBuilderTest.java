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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Assert;
import org.junit.Test;

import com.tmobile.ct.codeless.core.Suite;
import com.tmobile.ct.codeless.test.suite.SuiteImpl;

public class ExcelTestBuilderTest {

	@Test
	public void parseRowTest() throws Exception{
		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();
		File workbookFile = new File(s+"/src/test/resources/suites/test_google.xlsx");
		Workbook workbook;

		try {
			workbook = WorkbookFactory.create(workbookFile);
			Stream<Sheet> sheets = StreamSupport.stream(Spliterators.spliteratorUnknownSize(workbook.sheetIterator(), Spliterator.ORDERED), false);
			Suite suite = new SuiteImpl(s);
			com.tmobile.ct.codeless.core.Test test;

			for (Sheet sheet : sheets.toArray(Sheet[]::new)) {
				test = new ExcelTestBuilder().build(suite, sheet, "", null);
				Assert.assertNotNull(test);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

}
