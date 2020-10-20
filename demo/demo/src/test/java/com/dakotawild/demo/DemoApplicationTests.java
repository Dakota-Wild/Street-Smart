package com.dakotawild.demo;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Assert;
import org.junit.Test;


class DemoApplicationTests {

	private Money exampleMoney;

	void contextLoads() {
	}

	@Test
	public void testJMoney(){
		WebController tester = new WebController();
		exampleMoney = Money.parse("USD 58.23");
		CurrencyUnit usd = CurrencyUnit.of("USD");
		// Set up JMoney object with example methods

		Assert.assertEquals("$58.23", exampleMoney.toString());
	}

}
