package com.tmobile.ct.codeless.core;

import java.util.List;

public interface Component {

	List<Step> getSteps();
	
	void addStep(Step step);
	
	void setSteps(List<Step> steps);
	
	String getName();
	
	void setName(String name);
}
