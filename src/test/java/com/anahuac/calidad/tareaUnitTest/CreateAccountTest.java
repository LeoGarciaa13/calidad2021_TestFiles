package com.anahuac.calidad.tareaUnitTest;

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
public class CreateAccountTest {
	private String arg1;		//Value 1 (holder) 
	private int arg2;			//Value 2 (initial balance)
	private int arg3;			//Value 3 (zone)
	private int arg4;			//Value 4 (due)
	private int arg5;			//Expected value (balance)
	//private int arg6;			//Expected value (zone)
	private cuenta miCuenta; //Object
	
	// Set arguments to test
	public CreateAccountTest(String arg1, int arg2, int arg3, int arg4, int arg5){
		this.arg1 = arg1;
		this.arg2 = arg2;
		this.arg3 = arg3;
		this.arg4 = arg4;
		this.arg5 = arg5;
	}
	
	// Set parameters to test
	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][]{
			{"Cuenta de Juan", 200,1,75, 125},				// Zone 1
			{"Cuenta de Maria", 500, 2, 250, 250},			// Zone 2
			{"Cuenta de Ignacio", 1850, 3, 50, 1800},		// Zone 3
			}); 
	}

	@Before
	public void setUp() throws Exception {
		// Create new object
		miCuenta = new cuenta();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void Account_test() {
		// Using HamCrest 		
		// Exercise code to run and test
		//
		miCuenta.setHolder(this.arg1);
		String resHolder = miCuenta.getHolder(); 
		//
		miCuenta.setBalance(this.arg2);
		int resBalance = miCuenta.getBalance(); 
		//
		miCuenta.setZone(this.arg3);
		int resZone = miCuenta.getZone(); 
						
		// Verify
		assertThat(this.arg1, is(resHolder));
		assertThat(this.arg2, is(resBalance)); 
		assertThat(this.arg3, is(resZone)); 
	}
	
	@Test
	public void AccountCredit_test() {
		// Using HamCrest 		
		// Exercise code to run and test
		//
		miCuenta.setHolder(this.arg1);
		//
		miCuenta.setBalance(this.arg2); 
		//
		miCuenta.setZone(this.arg3);
		//
		miCuenta.debit(this.arg4);
		int Debit = miCuenta.getBalance(); 				
		// Verify
		assertThat(this.arg5, is(Debit));
		
	}

}
