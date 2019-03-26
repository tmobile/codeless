package com.tmobile.ct.codeless.test.component;

import java.io.File;

import org.apache.commons.io.FilenameUtils;

import com.tmobile.ct.codeless.files.ClassPathUtil;

public class ComonentLoader {

	public void load(String path) {
		File file = new File(ClassPathUtil.getAbsolutePath(path));
		String extension = FilenameUtils.getExtension(path);
		
		if(extension.equalsIgnoreCase("csv")) {
			//load csv
		}else {
			//load xls
		}
		
	}
}
