/**
 * 
 */
package com.tmobile.selenium.sam.action.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 * The Class ScreenShot.
 *
 * @author Rob Graff (RGraff1)
 */
public class ScreenShot {

	/**
	 * Take.
	 *
	 * @param driver the driver
	 * @param fileName the file name
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static String take(WebDriver driver, String fileName) throws IOException {

		try {
			String path = "/screenshot/" + fileName + ".png";
			File file = new File(path);

			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, file);

			return file.getAbsolutePath();
		} catch (Exception e) {
			return null;
		}

	}

}
