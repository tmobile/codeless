package com.tmobile.ct.codeless.ui.excel;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;

import com.tmobile.ct.codeless.core.Step;
import com.tmobile.ct.codeless.ui.assertion.SeleniumMethodType;
import com.tmobile.ct.codeless.ui.assertion.UiAssertionBuilder;
import com.tmobile.ct.codeless.ui.model.ControlElement;
import com.tmobile.ct.codeless.ui.model.yaml.YamlReader;
import com.tmobile.ct.codeless.ui.assertion.UiAssertionMethod;
import com.tmobile.ct.codeless.ui.assertion.UiSeleniumMethod;
import com.tmobile.ct.codeless.ui.page.modals.CtUiTestRow;

/**
 * The Class ExcelUiTestBuilder.
 *
 * @author Rob Graff
 */
public class ExcelUiTestBuilder {

	/** The formatter. */
	private DataFormatter formatter = new DataFormatter();

	/**
	 * Builds the.
	 *
	 * @param row the row
	 * @return the step
	 */
	public Step build(Row row) {
		CtUiTestRow step = new CtUiTestRow();
		ExcelUiTestRow testRow = new ExcelUiTestRow();
		for (Cell cell : row) {
			SuiteHeaders header = SuiteHeaders
					.parse(formatter.formatCellValue(cell.getSheet().getRow(0).getCell(cell.getColumnIndex())));
			String value = formatter.formatCellValue(cell);
			if (value != null && value.trim().length() > 0) {
				switch (header) {
				case STEP:
					testRow.setStep(value);
					step.setName(value);
					break;
				case ACTION:
					testRow.setAction(value);
					break;
				case TARGET:
					testRow.setTarget(value);
					break;
				case INPUT:
					testRow.setInput(value);
					break;
//				case WAITTIME_OVERRIDE:
//					testRow.setWaitTime_Override(value);
//					break;
//				case WAITTYPE:
//					testRow.setWaitType(value);
//					break;
//				case ACTION_OVERRIDE:
//					testRow.setAction_Override(value);
//					break;
				case TESTDATA:
					testRow.getTestData().add(value);
					break;
				default:
					break;
				}
			}
		}

		ControlElement ele = parseTarget(testRow);
		step.setControlElement(ele);
		step.setName(testRow.getStep());
		step.setStep(testRow);

		List<UiAssertionBuilder> assertions = parseAssertion(testRow, step);
		step.setAssertionBuilder(assertions);
		return step;
	}

	/**
	 * Parses the target.
	 *
	 * @param testRow the test row
	 * @return the control element
	 */
	private ControlElement parseTarget(ExcelUiTestRow testRow) {
		if (testRow.getTarget() != null && !testRow.getTarget().isEmpty()) {
			String[] target = testRow.getTarget().trim().split("[.]");
			if (target.length == 3) {
				String appName = target[0];
				String modelName = target[1];
				String targetName = target[2];
				Map<String, ControlElement> controls = YamlReader.ReadControl(appName,modelName);
				ControlElement webElement = controls.get(targetName);
				return webElement;
			}
		}
		return null;
	}

	/**
	 * Parses the assertion.
	 *
	 * @param testData the test data
	 * @param step the step
	 * @return the list
	 */
	private List<UiAssertionBuilder> parseAssertion(ExcelUiTestRow testData, CtUiTestRow step) {

		List<UiAssertionBuilder> assertions = new ArrayList<>();
		if (testData.getTestData() != null && testData.getTestData().size() > 0) {
			testData.getTestData().forEach(d -> {

				String[] assertions_data = d.trim().split("[,]");
				for (String data : assertions_data) {
					String[] originalParts = data.trim().split("::");
					String assertionMethodName = originalParts[0];
					SeleniumMethodType type = SeleniumMethodType.valueOf(originalParts[1]);
					String seleniumMethodName = null;
					Method seleniumMethod = null;
					String parameter = "";
					String expected = "";

					if (originalParts.length > 2) {
						seleniumMethodName = originalParts[2];
					}

					try {
						if (originalParts.length >= 4) {
							expected = originalParts[3];

							if (originalParts.length == 5) {
								parameter = originalParts[4];
							}
						} else if (originalParts.length == 3) {
							if (originalParts.length == 4) {
								parameter = originalParts[3];
							}
						}

						if (seleniumMethodName != null) {
							seleniumMethod = UiSeleniumMethod.getSeleniumMethod(seleniumMethodName, parameter, type);
						}

						Method assertionMethod = UiAssertionMethod.getAssertionMethod(assertionMethodName, expected);
						UiAssertionBuilder assertion = new UiAssertionBuilder(assertionMethod, expected, seleniumMethod,
								type, parameter);
						assertions.add(assertion);

					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					}
				}
			});
		}

		return assertions;
	}

}
