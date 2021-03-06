package com.unico.enterprise.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
 * Persistence class for Object GCD
 */

@Entity
public class GCD  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int computedGCD;

	public GCD() {
		
	}
	
	public GCD(int computedGCD) {
		this.computedGCD = computedGCD;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getComputedGCD() {
		return computedGCD;
	}

	public void setComputedGCD(int computedGCD) {
		this.computedGCD = computedGCD;
	}
}