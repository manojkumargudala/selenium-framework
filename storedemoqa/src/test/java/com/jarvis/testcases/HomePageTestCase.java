package com.jarvis.testcases;

import java.util.Iterator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.demoqa.exceldtos.EmployeeLogin;
import com.demoqa.selenium.BaseDriverInitilization;
import com.demoqa.testngdataprovider.ExcelReadDataProvider;

public class HomePageTestCase extends BaseDriverInitilization {
	// Test case 4
	@Test(dataProvider = "data-source", enabled = true)
	public void purchaseHistory(final EmployeeLogin emp) {
		System.out.println("in side the test case");
		System.out.println(emp.getEmployeedPassword());
		System.out.println(emp.getEmployeeLogin());
		System.out.println("***********************");
		// Ctrl+shift+C
		loadBaseUrl();

	}

	// Lazy initialization of the data provide of excel data
	// Normal initialization --> not used
	@DataProvider(name = "data-source")
	public Iterator<Object[]> dataOneByOne() {
		System.out.println("in data One by One");
		return new ExcelReadDataProvider("home_login.xlsx", "Sheet1");
	}
}
