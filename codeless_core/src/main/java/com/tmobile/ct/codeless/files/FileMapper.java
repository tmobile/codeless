package com.tmobile.ct.codeless.files;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

public class FileMapper {
	
	private Path baseDir;
	private String qualifier;
	private ConcurrentHashMap<String,FileDotIdentifier> files = new ConcurrentHashMap<>();

	public FileMapper(Path baseDir, String qualifier) {
		this.baseDir = baseDir;
		this.qualifier = qualifier;
	}

	public ConcurrentHashMap<String, FileDotIdentifier> map() {

	    search("", "");
	    return files;
	}
	
	public void search(String directoryName, String currentDot) {
		File directory = Paths.get(baseDir.toAbsolutePath().toString(), directoryName).toFile();


		// Get all files from a directory.
		File[] fList = directory.listFiles();
		if (fList == null || fList.length == 0) {
			return;
		}

		for (File file : fList) {
			if (file.isFile()) {
				String key = currentDot + "." + FilenameUtils.removeExtension(file.getName());
				FileDotIdentifier fdId = new FileDotIdentifier(key, file.getAbsolutePath(),
						FilenameUtils.getExtension(file.getName()), qualifier, file.getName());

				files.put(key, fdId);
			} else if (file.isDirectory()) {
				String dot = "";
				if(StringUtils.isBlank(currentDot)) {
					dot = file.getName();
				}else {
					dot = currentDot + "." + file.getName();
				}
				search(directoryName + File.separator + file.getName(), dot);
			}
		}

	}
}
