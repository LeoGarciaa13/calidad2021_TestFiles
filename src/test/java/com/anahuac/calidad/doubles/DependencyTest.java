package com.anahuac.calidad.doubles;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
// Import mockito
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.*;
// Import hamcrest
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class DependencyTest {
	
	Dependency dependency;

	@Before
	public void setUp() throws Exception {
		dependency = Mockito.mock(Dependency.class);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		//System.out.println("Result = "+ dependency.addTwo(5)); // Returns 0 because of the mock
		// Verifying that the method is null with the mocked dependency
		assertThat(dependency.getClassName(),is(nullValue()));
	}
	
	
	private void setBehavior() {
		
		// Set the behavior of the instance
		when(dependency.getClassName()).thenReturn("Nombre de la clase");
		
	}
	
	// Test for a mocked value
	@Test
	public void mockValue_test() {
		// Set expected result of the test
		String expectedResult = "Nombre de la clase"; 
		// Calling the behavior method
		setBehavior(); 
		// Set running result of the test
		String runningResult = dependency.getClassName(); 
		// Using Hamcrest 
		// Compare the results within a mocked behavior
		assertThat(expectedResult,is(runningResult)); 
	}
	
	// Test for a mocked exception
	@Test(expected=IllegalArgumentException.class)
	public void mockException_Test(){
		// Set the behavior of the instance
		when(dependency.getClassName()).thenThrow(IllegalArgumentException.class);
		// Exercise the test of mock
		dependency.getClassName();
		
	}
	
	// Test for a mocked real class
	@Test
	public void mockReal_Test(){
		// Set the behavior of the instance
		when(dependency.getClassName()).thenCallRealMethod();
		// Exercise the test of mock			
		assertThat(dependency.getClassName(),is(dependency.getClass().getSimpleName()));
			
	}
	
	// Test for a mocked argument
	@Test
	public void mockArgument_Test(){
		int expectedResult = 2;
		// Set the behavior of the instance
		when(dependency.addTwo(0)).thenReturn(2);
		// Exercise the test of mock	
		int runningResult = dependency.addTwo(0);
		// Verify
		assertThat(expectedResult,is(runningResult)); 
				
	}
	
	// Test for a mocked argument matchers
	@Test
	public void mockAnyArg_Test(){
		int expectedResult = 10;
		// Set the behavior of the instance
		when(dependency.addTwo(anyInt())).thenReturn(10);
		// Exercise the test of mock	
		int runningResult = dependency.addTwo(0);
		// Verify
		assertThat(expectedResult,is(runningResult)); 
		assertThat(10,is(dependency.addTwo(20))); 
					
	}
	
	// Test for a mocked argument matchers
	@Test
	public void mockAnswer_Test(){
		// Set the behavior of the instance
		when(dependency.addTwo(anyInt())).thenAnswer(new Answer<Integer>() {
						// Method within the class
						public Integer answer(InvocationOnMock invocation) throws Throwable{
							// Set behavior in every invocation 
							int arg = (Integer) invocation.getArguments()[0]; 
							// Return the invoked value
							return arg + 10; 
						}
					}
				);
		// Exercise the test of mock	
		// Verify
		assertThat(20,is(dependency.addTwo(10))); 
		assertThat(49,is(dependency.addTwo(39))); 
						
	}


}
