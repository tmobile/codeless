package com.tmobile.ct.codeless.files;

import org.junit.Test;
import org.junit.Assert;

public class ClassPathUtilTest {
	@Test
	public void itShouldGetPath() {
		// Make sure it is not null
		Assert.assertNotNull(ClassPathUtil.getAbsolutePath("/samples/readme.md"));
	}
	
	@Test
	public void itShouldExist() {
		// Make sure it exists
		Assert.assertTrue(ClassPathUtil.exists("/samples/readme.md"));
	}
	
	@Test
	public void itShouldNotExist() {
		// Make sure it does not exist
		Assert.assertFalse(ClassPathUtil.exists("/does/not/exist.md"));
	}
}
