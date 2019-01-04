package com.tmobile.ct.codeless.ui.page.modals.test;

import org.junit.Assert;
import org.junit.Test;

import com.tmobile.ct.codeless.ui.excel.test.ExcelUiTestRowTest;
import com.tmobile.ct.codeless.ui.page.modals.CtUiTestRow;
import com.tmobile.ct.codeless.ui.yaml.test.ControlElementTest;

public class CtUiTestRowTest {

	private static CtUiTestRow ctUiTestRow = new CtUiTestRow();

	@Test
	public void testSetAndGetName() {
		String expected = "Name";
		ctUiTestRow.setName(expected);
		Assert.assertEquals(ctUiTestRow.getName(), expected);
	}

	@Test
	public void testExcelUiTestRow() {
		ctUiTestRow.setStep(ExcelUiTestRowTest.createExcelUiTestRow());
		Assert.assertNotNull(ctUiTestRow.getStep());
	}

	@Test
	public void testControlElement() {
		ctUiTestRow.setControlElement(ControlElementTest.createControlElement());
		Assert.assertNotNull(ctUiTestRow.getControlElement());
	}

}
