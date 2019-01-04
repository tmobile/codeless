package com.tmobile.ct.codeless.data;

import org.junit.Test;
import org.junit.Assert;

public class BasicSourcedDataMapTest {
	@Test
	public void itShouldCreate() {
		BasicSourcedDataMap bsdm = new BasicSourcedDataMap();
		Assert.assertNotNull(bsdm);
	}
}
