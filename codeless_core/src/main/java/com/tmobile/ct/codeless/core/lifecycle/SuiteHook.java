package com.tmobile.ct.codeless.core.lifecycle;

import com.tmobile.ct.codeless.core.Suite;
import com.tmobile.ct.codeless.core.plugin.Plugin;

public interface SuiteHook extends Plugin {

	void beforeSuite(Suite suite);
	
	void afterSuite(Suite suite);
	
}
