package com.company.microservices.service;

import java.util.List;

import com.company.microservices.data.model.Employee;

public interface EmployeeService {

	/**
	 *
	 * @return
	 */
	List<Employee> getEmployees();

	/**
	 *
	 * @param employeeId
	 * @return
	 */
	Employee getEmployee(String employeeId);

	/**
	 *
	 * @param employee
	 * @return
	 */
	Employee createEmployee(Employee employee);

	/**
	 *
	 * @param employeeId
	 * @param employee
	 * @return
	 */
	Employee updateEmployee(String employeeId, Employee employee);

	/**
	 *
	 * @param employeeId
	 * @return
	 */
	Employee deleteEmployee(String employeeId);
}
