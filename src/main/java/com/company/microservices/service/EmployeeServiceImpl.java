package com.company.microservices.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.microservices.data.model.Employee;
import com.company.microservices.data.repository.EmployeeRepository;
import com.company.microservices.exceptions.EmployeeNotFoundException;
import com.company.microservices.util.ConversionUtil;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public List<Employee> getEmployees(String department) {
		List<Employee> employeeList = new ArrayList<>();
		if (null == department) {
			Iterable<Employee> employees = employeeRepository.findAll();
			employeeList = ConversionUtil.toArrayList(employees.iterator());
		} else {
			employeeList = employeeRepository.findEmployeeByDepartment(department);
		}

		return employeeList;
	}

	@Override
	public List<Employee> getEmployeesFromDepartment(String department) {
		// TODO Auto-generated method stub
		return employeeRepository.findEmployeeByDepartment(department);
	}

	@Override
	public Employee getEmployee(String employeeId) {
		Employee employee = employeeRepository.findOne(employeeId);
		if (null == employee) {
			throw new EmployeeNotFoundException("1001", "Employee not found");
		}
		return employee;
	}

	@Override
	public Employee createEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public Employee updateEmployee(String employeeId, Employee employee) {
		Employee employeeFromDB = getEmployee(employeeId);
		updateEmployeeDetails(employeeFromDB, employee);
		return employeeRepository.save(employeeFromDB);
	}

	@Override
	public Employee deleteEmployee(String employeeId) {
		Employee employeeToDelete = getEmployee(employeeId);
		employeeRepository.delete(employeeToDelete);
		return employeeToDelete;
	}

	private void updateEmployeeDetails(Employee employeeFromDb, Employee employee) {
		employeeFromDb.setName(employee.getName());
		employeeFromDb.setDepartment(employee.getDepartment());
	}
}
