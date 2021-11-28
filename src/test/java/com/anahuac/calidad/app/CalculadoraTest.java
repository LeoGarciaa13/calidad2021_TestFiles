package com.anahuac.calidad.app;

// JUnit 4 imports 
//import static org.junit.Assert.*;

// HamCrest Imports 
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

// JUNit Testers
import org.junit.After;
//import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

// Package Imports
import com.anahuac.calidad.app.Calculadora;

public class CalculadoraTest {

	private Calculadora miCalculadora;
	
	@Before
	public void setUp() throws Exception {
		// Create new object
		miCalculadora = new Calculadora(); 
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void Sumatest() {
		/*
		// Using JUnit 4
		// Set the value we are waiting for
		float resEsperado = 4; 
		
		// Exercise code to run and test
		float resEjecucion = miCalculadora.suma(2.0f, 2.0f);
		
		// Verify
		Assert.assertEquals(resEsperado, resEjecucion, .01); */
		
		// Nuevo comenatrio
		// Using HamCrest 
		// Set the value we are waiting for
		double resEsperado = 4; 
				
		// Exercise code to run and test
		double resEjecucion = miCalculadora.suma(2.0f, 2.0f);
		System.out.println(resEjecucion);		
		// Verify
		assertThat(resEsperado, is(closeTo(resEjecucion, .01))); 
		
	}
	
	@Test
	public void Restatest() {
		// Using HamCrest 
		// Set the value we are waiting for
		double resEsperado = 5; 
				
		// Exercise code to run and test
		double resEjecucion = miCalculadora.resta(10.0f, 5.0f);
				
		// Verify
		assertThat(resEsperado, is(closeTo(resEjecucion, .01))); 
		
	}
	
	@Test
	public void Division_Entera_test() {
		// Using HamCrest 
		// Set the value we are waiting for
		double resEsperado = 2; 
				
		// Exercise code to run and test
		double resEjecucion = miCalculadora.divisionEntera(5, 2);
				
		// Verify
		assertThat(resEsperado, is(resEjecucion)); 
		
	}
	
	
	@Test
	public void Division_Decimal_test() {
		// Using HamCrest 
		// Set the value we are waiting for
		double resEsperado = 2.5f; 
				
		// Exercise code to run and test
		double resEjecucion = miCalculadora.division(5.0f, 2.0f);
				
		// Verify
		assertThat(resEsperado, is(closeTo(resEjecucion, .01))); 
		
	}
	
	@Test
	public void Division_Entera_Negativa_test() {
		// Using HamCrest 
		// Set the value we are waiting for
		double resEsperado = -2f; 
				
		// Exercise code to run and test
		double resEjecucion = miCalculadora.division(10.0f, -5.0f);
				
		// Verify
		assertThat(resEsperado, is(closeTo(resEjecucion, .01))); 
		
	}

	@Test
	public void Division_Decimal_Negativa_test() {
		// Using HamCrest 
		// Set the value we are waiting for
		double resEsperado = -2.5f; 
				
		// Exercise code to run and test
		double resEjecucion = miCalculadora.division(5.0f, -2.0f);
				
		// Verify
		assertThat(resEsperado, is(closeTo(resEjecucion, .01))); 
		
	}
	
	@Test
	public void Division_Cero_Numerador_test() {
		// Using HamCrest 
		// Set the value we are waiting for
		double resEsperado = 0.0f; 
				
		// Exercise code to run and test
		double resEjecucion = miCalculadora.division(0.0f, 5.0f);
				
		// Verify
		assertThat(resEsperado, is(closeTo(resEjecucion, .01))); 
		
	}
	
	@Test(expected = ArithmeticException.class)
	public void Division_Cero_Denominador_test() {
		// Using HamCrest 
		// Set the value we are waiting for
		double resEsperado = 2; 
				
		// Exercise code to run and test
		double resEjecucion = miCalculadora.divisionEntera(5,0);
				
		// Verify
		// We catch the exception with the test annotation and the expected clause
		
	}
}
