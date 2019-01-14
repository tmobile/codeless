package com.tmobile.ct.codeless.test;

import com.tmobile.ct.codeless.core.Execution;

public class ExecutionContainer {

	private static Execution execution;
	
	public static void setExecution(Execution newExecution){
		execution = newExecution;
	}
	
	public static Execution getExecution(){
		return execution;
	}
}
