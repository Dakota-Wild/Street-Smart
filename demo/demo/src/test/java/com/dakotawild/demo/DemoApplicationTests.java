package com.dakotawild.demo;

import org.junit.Assert;


class DemoApplicationTests {


	void contextLoads() {
	}

	@Test
	public void testJMoney(){
		Money exampleMoney = Money.parse("USD 58.23");
		CurrencyUnit usd = CurrencyUnit.of("USD");
		money = money.plus(Money.of(usd, 12.43d));
		String result = money.toString();
		Assert.assertEquals("$70.46", result);
	}

}
