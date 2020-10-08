package com.dakotawild.demo;

import org.junit.Assert;


class DemoApplicationTests {

	private Money exampleMoney;

	void contextLoads() {
	}

	@Test
	public void testJMoney(){
		DemoApplicationTests tester = new DemoApplicationTests();
		exampleMoney = Money.parse("USD 58.23");
		CurrencyUnit usd = CurrencyUnit.of("USD");
		// Set up JMoney object with example methods

		Assert.assertEquals("$58.23", tester.exampleMoney.toString());
	}

}
