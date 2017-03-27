package com.unico.enterprise.utils;

public class GCDCalculator {
	
	/**
	 * This method calculates the GCD of two numbers
	 * @param a
	 * @param b
	 * @return
	 */
	public static int getGcd(int a, int b) {
		if (a == 0)
			return b;

		while (b != 0) {
			if (a > b)
				a = a - b;
			else
				b = b - a;
		}
		return a;
	}
}
