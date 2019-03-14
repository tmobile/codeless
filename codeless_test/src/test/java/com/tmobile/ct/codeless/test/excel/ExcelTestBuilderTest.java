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
