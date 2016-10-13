package com.diginsite.microservices;

import java.util.ArrayList;
import java.util.List;

import com.company.microservices.data.model.Employee;

public class ApiControllerTestDataFactory {

	public static Employee getEmployeePayloadMockData() {

		Employee employee1 = new Employee();
		employee1.setName("Shahaf Famil");
		employee1.setDepartment("Product");
		return employee1;
	}

	public static List<Employee> getEmployeesMockData() {
		List<Employee> employeeList = new ArrayList<>();

		Employee employee1 = new Employee();
		employee1.setName("Shahaf Famil");
		employee1.setDepartment("Product");
		employeeList.add(employee1);

		Employee employee2 = new Employee();
		employee2.setName("Pradeep Makrucki");
		employee2.setDepartment("Engineering");
		employeeList.add(employee2);

		return new ArrayList<>(employeeList);
	}
}
