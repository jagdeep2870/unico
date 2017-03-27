package com.unico.enterprise.utils;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class GCDCalculatorTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testGetGcd() {
		assertEquals(GCDCalculator.getGcd(20, 16), 4);
		assertEquals(GCDCalculator.getGcd(200, 100), 100);
		assertEquals(GCDCalculator.getGcd(100, 80), 20);
		assertEquals(GCDCalculator.getGcd(25, 75), 25);
		
	}

}
