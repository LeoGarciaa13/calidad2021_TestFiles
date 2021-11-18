package com.anahuac.calidad.app;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class CalculadoraParametrizadaTest {
	private double arg1;	//Value 1
	private double arg2;	//Value 2
	private double arg3;	//Expected value
	private Calculadora miCalculadora; //Object
	
	// Set arguments to test
	public CalculadoraParametrizadaTest(double arg1,double arg2,double arg3){
		this.arg1 = arg1;
		this.arg2 = arg2;
		this.arg3 = arg3; 
	}
	
	// Set parameters to test
	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][]{
			{4,2,2},			// Integers
			{6,-3,-2},			// Negative Integer
			{5,5,1},			// Same number
			{5,2,2.5},			// Decimal
			{5,-2,-2.5},		// Negative Decimal
			{10,0,Double.POSITIVE_INFINITY},	//Infinity 
			{0,0,Double.NaN}	// Not a number
			
			}); 
		
	}

	@Before
	public void setUp() throws Exception {
		// Create new object
		miCalculadora = new Calculadora(); 
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void Division_test() {
		// Using HamCrest 		
		// Exercise code to run and test
		double resEjecucion = miCalculadora.division(this.arg1, this.arg2);
				
		// Verify
		assertThat(this.arg3, is(resEjecucion)); 
		
	}

}
