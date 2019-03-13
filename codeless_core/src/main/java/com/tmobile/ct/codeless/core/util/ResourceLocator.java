package com.tmobile.ct.codeless.core.util;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

public class ResourceLocator {
    /**
     * Returns the loaded resource from classpath or filesyste,
     * @param orginalFileName
     * @return
     */
    public static Optional<File> getFile(String orginalFileName) {
        File file = new File(orginalFileName);
        if (!file.exists()) {
            if (!orginalFileName.contains(".")) {
                // its a folder, create it if it doesn't exist
                file.mkdirs();
            }

            if (!file.exists()) {
                // try this way
                URL resource = ResourceLocator.class.getClassLoader().getResource(orginalFileName);
                String filePath = resource.getPath().replace("%25", "%");

                if (resource != null) {
                    file = new File(filePath);
                }
            }
        }

        return Optional.of(file).filter(currFile -> currFile.exists());
    }

    public static String getRequestFromFile(String fileLocation) throws Exception {
		File file = null;
		String filePath = fileLocation;
		try {
			file = getFile(filePath).get();
			String requestBody = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));

			return requestBody;
		} catch (Exception e) {
			throw e;
		}
	}

}
