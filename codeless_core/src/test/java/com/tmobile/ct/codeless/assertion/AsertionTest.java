/*******************************************************************************
 * * Copyright 2018 T Mobile, Inc. or its affiliates. All Rights Reserved.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 *  * use this file except in compliance with the License.  You may obtain a copy
 *  * of the License at
 *  *
 *  *   http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 *  * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 *  * License for the specific language governing permissions and limitations under
 *  * the License.
 ******************************************************************************/
package com.tmobile.ct.codeless.assertion;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.junit.Test;

import com.tmobile.ct.codeless.core.Result;
import com.tmobile.ct.codeless.core.Status;


public class AsertionTest {

	@Test
	public void stringToAssertion() throws Throwable{
		String exp = "foo";
		String act = "foo";
		
		Object assertion = assertThat(exp);
		try{
			assertion.getClass().getMethod("isEqualTo", Object.class).invoke(assertion,act);
		}catch(InvocationTargetException ex){
			throw ExceptionUtils.getRootCause(ex);
		}
//		assertThat(exp).isEqualTo(act);
		
//		Object a = assertThat(exp);
//		method.invoke(act);
	}
	
	@Test
	public void assertJAssertionShouldRun(){

		String exp = "foo";
		String act = "bar";
		String method = "isEqualTo";
		AssertJAssertion<String> assertion = new AssertJAssertion<>(exp, act, method, String.class );
		
		try {
			assertion.run();
		} catch (Exception e) {
			// do nothing on purpose, test needs to track results below, 
			// not exceptions from run
		}
		
		assertThat(assertion.getStatus()).isEqualTo(Status.COMPLETE);
		assertThat(assertion.getResult()).isEqualTo(Result.FAIL);
		
		System.out.println(ExceptionUtils.getStackTrace(assertion.getFailureCause()));
		
		
	}
	
	@Test
	public void assertJAssertionOnNumberShouldRun(){
		
//		assertThat(Long.valueOf(99L))
//			.describedAs("user_id")
//			.isGreaterThan(Long.valueOf(1L));

		
		Long exp = 99L;
		Long act = 1L;
		String method = "isGreaterThan";
		AssertJAssertion<Long> assertion = new AssertJAssertion<>(exp, act, method, Long.class );
		
		try {
			assertion.run();
		} catch (Exception e) {
			// do nothing on purpose, test needs to track results below, 
			// not exceptions from run
		}
		
		assertThat(assertion.getStatus()).isEqualTo(Status.COMPLETE);
		assertThat(assertion.getResult()).isEqualTo(Result.FAIL);
		
		System.out.println(ExceptionUtils.getStackTrace(assertion.getFailureCause()));
	}
	
	@Test
	public void getClassFromGeneric(){
		
		Long exp = 1L;
		Long act = 99L;
		String method = "isGreaterThan";
		AssertJAssertion<Long> assertion = new AssertJAssertion<>(exp, act, method, Long.class);
		
//		Class<?> clazz = GenericTypeResolver.resolveTypeArgument(assertion.getClass(), Long.class);
		
		System.out.println(assertion.getTypeClass());
		
		assertThat(assertion.getTypeClass()).isEqualTo(Long.class);
	}
}
