package com.tmobile.ct.codeless.files;

import java.nio.file.Paths;
import java.util.concurrent.ConcurrentHashMap;

import static org.assertj.core.api.Assertions.*;
import org.junit.Test;

public class FileMapperTest {
	
	@Test
	public void itShouldMapNestedFolders() {
		String path = ClassPathUtil.getAbsolutePath("/components");
		
		FileMapper mapper = new FileMapper(Paths.get(path), "component");
		
		ConcurrentHashMap<String, FileDotIdentifier> files = mapper.map();
		
		assertThat(files.size()).isEqualTo(4);
	}

}
