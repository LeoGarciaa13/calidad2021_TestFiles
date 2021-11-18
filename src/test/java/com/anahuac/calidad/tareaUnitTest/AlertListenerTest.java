package com.anahuac.calidad.tareaUnitTest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
//Import Mockito
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.*;

public class AlertListenerTest {
	
	cuenta miCuenta; //Object
	private alertListener miAlerta; 
	

	@Before
	public void setUp() throws Exception {
		// Declare the mocking object
		miAlerta = Mockito.mock(alertListener.class);
		// Declare the object
		miCuenta = new cuenta(); 
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void Verify_test() {
		// Initialize values
		miCuenta.setZone(1);
		miCuenta.setBalance(300);
		miCuenta.setHolder("Cuenta x");
		miCuenta.setAlerts(miAlerta);
		// Set running result of the test
		miCuenta.debit(250);
		// Verify that indeed the method is called
		verify(miAlerta).sendAlert(anyString());
	}

}
