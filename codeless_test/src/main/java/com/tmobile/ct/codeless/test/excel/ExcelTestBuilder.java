package com.tmobile.ct.codeless.test.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.tmobile.ct.codeless.core.Step;
import com.tmobile.ct.codeless.core.Suite;
import com.tmobile.ct.codeless.core.Test;
import com.tmobile.ct.codeless.core.TestBuilder;
import com.tmobile.ct.codeless.core.TestData;
import com.tmobile.ct.codeless.core.datastructure.SourcedValue;
import com.tmobile.ct.codeless.data.BasicTestData;
import com.tmobile.ct.codeless.data.SourcedDataItem;
import com.tmobile.ct.codeless.service.test.excel.ExcelServiceCallBuilder;
import com.tmobile.ct.codeless.ui.excel.ExcelUiStepBuilder;

/**
 * The Class ExcelTestBuilder.
 *
 * @author Rob Graff
 */
public class ExcelTestBuilder implements TestBuilder{

	/** The formatter. */
	private DataFormatter formatter = new DataFormatter();

	/** The test. */
	ExcelTest test = new ExcelTest();

	/** The data. */
	TestData data = new BasicTestData();

	/**
	 * Builds the.
	 *
	 * @param suite the suite
	 * @param sheet the sheet
	 * @param name the name
	 * @return the test
	 */
	public Test build(Suite suite, Sheet sheet, String name, TestData testData){
		test.setSuite(suite);
		test.setName(name);
		test.setConfig(suite.getConfig());
		test.setTestData(testData);

		int count = -1;
		for(Row row: sheet){
			count++;
			if(count <1) continue;
			Step step = parseRow(row);
			if(step != null){
				test.addStep(step);
			}
		}

		return test;
	}


	/**
	 * Parses the row.
	 *
	 * @param row the row
	 * @return the step
	 */
	private Step parseRow(Row row){
		if(row.getCell(0) != null){
		String stepName = row.getCell(0).getStringCellValue();
		if(stepName == null || stepName == "" || stepName.startsWith("#")){
			return null;
		}
		}
		Step step;
		if(getSafeStringFromCell(row.getCell(1)).equalsIgnoreCase("SERVICECALL")){
			step = new ExcelServiceCallBuilder().buildHybrid(test, row);
		}else if (getSafeStringFromCell(row.getCell(1)).equalsIgnoreCase("CONFIG")){
			parseConfigStep(row);
			return null;
		}else{
			step = new ExcelUiStepBuilder().build(row, test.getTestData());
		}
		step.setTest(test);
		return step;
	}

	/**
	 * Parses the config step.
	 *
	 * @param row the row
	 */
	private void parseConfigStep(Row row){
		for(Cell cell : row){
			String cellvalue = getSafeStringFromCell(cell);
			if(getSafeStringFromCell(cell).contains("::")){
				String[] parts = cellvalue.split("::");
				String key = parts[0];
				String value = parts[1];
				SourcedValue<String> sourcedValue = new SourcedValue<String>();
				sourcedValue.setValue(value);
				sourcedValue.setSource("Test-Specific-Config");
				test.getConfig().put(parts[0], new SourcedDataItem<String,String>(key, sourcedValue));
			}
		}
	}

	/**
	 * Gets the safe string from cell.
	 *
	 * @param cell the cell
	 * @return the safe string from cell
	 */
	private String getSafeStringFromCell(Cell cell){
		return formatter.formatCellValue(cell).trim().replace(" ", "");
	}

	/* (non-Javadoc)
	 * @see com.tmobile.ct.codeless.core.TestBuilder#getTest()
	 */
	@Override
	public Test getTest() {
		return test;
	}

}
