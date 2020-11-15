package com.dakotawild.streetsmart;

import org.junit.Test;
import org.junit.Assert;

import org.apache.commons.math3.random.MersenneTwister;

public class DemoTestMath {
	
	private int randomNum = -1;

	void contextLoads() {
	}

	@Test
	public void testMath(){
		DemoTestMath tester = new DemoTestMath();
		MersenneTwister rng = new MersenneTwister();
		this.randomNum = rng.nextInt() % 100;

		Assert.assertTrue("The random number should be [-100, 100]", tester.randomNum >= -100 && tester.randomNum <= 100);
	}
}
