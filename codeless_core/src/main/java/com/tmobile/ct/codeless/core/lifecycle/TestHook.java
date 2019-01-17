package com.tmobile.ct.codeless.core.lifecycle;

import com.tmobile.ct.codeless.core.Test;
import com.tmobile.ct.codeless.core.plugin.Plugin;

public interface TestHook extends Plugin {

	void beforeTest(Test test);
	
	void afterTest(Test test);
}
