package com.demoqa.testngdataprovider;

import java.util.Iterator;
import java.util.List;

import com.demoqa.exceldtos.EmployeeLogin;

public class ExcelReadDataProvider implements Iterator<Object[]> {
	private int rowIndex = 2;
	private final ExcelReader excelRead;
	private final String sheetName;

	public ExcelReadDataProvider(String fileName, String sheetName) {
		excelRead = new ExcelReader(fileName);
		this.sheetName = sheetName;
	}

	@Override
	public boolean hasNext() {
		System.out.println("row index" + rowIndex);
		System.out.println("number of rows in" + excelRead.getRowCount(sheetName));
		return (rowIndex <= excelRead.getRowCount(sheetName));
	}

	@Override
	public Object[] next() {
		List<String> lst = excelRead.getRowData(sheetName, rowIndex);
		// System.out.println(lst.get(0) + lst.get(1) + lst.get(2));
		EmployeeLogin emp = new EmployeeLogin();
		if (lst.size() > 0) {
			emp.setEmployeeLogin(lst.get(0));
			emp.setEmployeedPassword(lst.get(1));
			emp.setIsValidUser(lst.get(2));
		}
		rowIndex++;
		return new Object[] { emp };
	}

}
