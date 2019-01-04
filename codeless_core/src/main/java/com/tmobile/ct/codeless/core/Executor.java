package com.tmobile.ct.codeless.core;

/**
 * The Class Executor.
 *
 * @author Rob Graff
 */
public class Executor {

	/**
	 * Run.
	 *
	 * @param executable the executable
	 */
	public void run(Executable executable){
		try{
			executable.run();
		}catch(Exception e){
//			e.printStackTrace();
			executable.fail(e);
			throw e;
		}
	}
}
