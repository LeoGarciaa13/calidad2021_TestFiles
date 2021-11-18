package com.anahuac.calidad.tareaUnitTest;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
// HashMap
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

// Mockito Imports
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
// Hamcrest Imports
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;




public class TransactionsTest {
	// Initialize DAO
	private cuenta DAO; 
	// INitialize HasMap
	public HashMap <String, Integer> cuentaBanco; 
	// Initialize object
	cuenta miCuenta;
	int TotIteraciones = 0; 
	
	
	@Before
	public void setUp() throws Exception {
		DAO = Mockito.mock(cuenta.class);
		cuentaBanco = new HashMap <String, Integer>();
		// Declare the object with parameters
		miCuenta = new cuenta(); 
		// Set up values of miCuenta
		miCuenta.setHolder("Cuenta x");
		miCuenta.setZone(2);
		miCuenta.setBalance(500);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		// Set when behaviors 
		// Set behaviors for adding an payment (+-) 
		when(DAO.abono(anyInt())).thenAnswer(new Answer<Integer>() {
			// Method within the class
			public Integer answer(InvocationOnMock invocation) throws Throwable{
				// Set behavior in every invocation 
				int arg = (Integer) invocation.getArguments()[0]; 
				cuentaBanco.put(anyString(), arg); 
				// Return the invoked value
				return cuentaBanco.size(); 
				}
			}
		);
		
		// Set behaviors for consulting the commissions 
		when(DAO.consultarComisiones()).thenAnswer(new Answer<Integer>() {
			// Method within the class
			public Integer answer(InvocationOnMock invocation) throws Throwable{
				// Set behavior in every invocation  
				for (Iterator<HashMap.Entry<String, Integer>> 
						entries = cuentaBanco.entrySet().iterator(); entries.hasNext(); ) {
					HashMap.Entry<String, Integer> entry = entries.next();
					int arg = (Integer) invocation.getArguments()[0]; 
					int operacion = arg * miCuenta.getPorcentaje(); 
				    TotIteraciones = operacion++;
					}	 
				// Return the invoked value
				return TotIteraciones; 
				}
			}
		);
		 
		// Exercise code for the test
		miCuenta.abono(100);
		miCuenta.abono(200);
		int total = miCuenta.getBalance();
		// Verify 
		assertThat(total, is(miCuenta.consultarComisiones()));
		
	}

}
