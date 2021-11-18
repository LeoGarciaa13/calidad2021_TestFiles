package com.anahuac.calidad.app;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FirstTest {

	@Before
	public void setUp() throws Exception {
		System.out.println("Este es el before");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("Este es el after");
	}

	@Test
	public void Firsttest() {
		System.out.println("Primer Test");
		fail("Not yet implemented");
	}
	
	@Test
	public void Secondtest() {
		System.out.println("Segundo Test");
		fail("Not yet implemented");
	}
	
	@Test
	public void Thirdtest() {
		System.out.println("Tercer Test");
		fail("Not yet implemented");
	}

}
