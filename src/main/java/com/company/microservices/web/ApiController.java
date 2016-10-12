package com.company.microservices.web;

import io.swagger.annotations.ApiOperation;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.company.microservices.data.model.Employee;
import com.company.microservices.service.EmployeeService;

@RestController
public class ApiController {

	/**
	 * Employee Service
	 */
	@Autowired
	EmployeeService employeeService;

	/**
	 * Returns a List of Employees
	 * @return
	 */
	@ApiOperation(value="Get Employees", notes = "Returns List of Employees",
			produces = "application/json",
			response = Employee.class, responseContainer = "List")
	@RequestMapping(value = "/v1/employees",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Employee>> getEmployees() {
		List<Employee> employees = employeeService.getEmployees();
		return new ResponseEntity<Collection<Employee>>(employees, HttpStatus.OK);
	}

	@ApiOperation(value="Get Employee", notes = "Returns Employee for the provided Employee Id",
			produces = "application/json",
			response = Employee.class, responseContainer = "Employee")
	@RequestMapping(value = "/v1/employees/{employeeId}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> getEmployee(@PathVariable("employeeId") String employeeId ) {

		Employee employee = employeeService.getEmployee(employeeId);

		return Optional.of(employee)
                .map(u -> new ResponseEntity<Employee>(u, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@ApiOperation(value="Create Employee", notes = "Creates an Employee",
			produces = "application/json", consumes = "application/json",
			response = Employee.class, responseContainer = "Employee")
	@RequestMapping(value = "/v1/employees",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {

		Employee emp = employeeService.createEmployee(employee);
		return Optional.of(emp)
                .map(u -> new ResponseEntity<Employee>(u, HttpStatus.CREATED))
                .orElse(new ResponseEntity<>(HttpStatus.CONFLICT));
	}

	@ApiOperation(value="Update Employee", notes = "Updates an Employee",
			produces = "application/json", consumes = "application/json",
			response = Employee.class, responseContainer = "Employee")
	@RequestMapping(value = "/v1/employees/{employeeId}",
			method = RequestMethod.PUT,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> updateEmployee(@PathVariable("employeeId") String employeeId,
														@RequestBody Employee employee) {
		Employee emp = employeeService.updateEmployee(employeeId, employee);

		return Optional.of(emp)
                .map(u -> new ResponseEntity<Employee>(u, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@ApiOperation(value="Delete Employee", notes = "Deletes an Employee",
			produces = "application/json", consumes = "application/json",
			response = Employee.class, responseContainer = "Employee")
	@RequestMapping(value = "/v1/employees/{employeeId}",
			method = RequestMethod.DELETE,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> deleteEmployee(@PathVariable("employeeId") String employeeId) {

		Employee emp = employeeService.deleteEmployee(employeeId);

		return Optional.of(emp)
                .map(u -> new ResponseEntity<Employee>(u, HttpStatus.NO_CONTENT))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
}
