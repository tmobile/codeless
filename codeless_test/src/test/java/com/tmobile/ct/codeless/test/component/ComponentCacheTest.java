package com.tmobile.ct.codeless.test.component;

import java.util.List;
import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

import com.tmobile.ct.codeless.core.Step;

public class ComponentCacheTest {

	@Test
	public void itShouldLoadComponent() {
		List<Step> steps = ComponentCache.getComponent("test.alpha");
		
//		System.out.println(steps);
		
		assertThat(steps.size()).isEqualTo(2);
	}
}
