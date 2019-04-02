package com.tmobile.ct.codeless.test.component;

import java.util.ArrayList;
import java.util.List;

import com.tmobile.ct.codeless.core.Component;
import com.tmobile.ct.codeless.core.Step;

public class ComponentImpl implements Component{

	private List<Step> steps = new ArrayList<>();
	private String name = "";
	
	@Override
	public List<Step> getSteps() {
		return steps;
	}

	@Override
	public void addStep(Step step) {
		steps.add(step);
	}

	@Override
	public void setSteps(List<Step> steps) {
		this.steps = steps;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

}
