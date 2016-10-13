package com.company.microservices.web;

import io.swagger.annotations.ApiOperation;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.microservices.data.model.Employee;
import com.company.microservices.data.model.Profile;
import com.company.microservices.service.EmployeeService;
import com.company.microservices.service.LinkedinService;

@RestController
public class ApiController {

	/**
	 * Employee Service
	 */
	private final EmployeeService employeeService;

	private final LinkedinService linkedinService;

	@Autowired
	public ApiController(EmployeeService employeeService, LinkedinService linkedinService) {
		this.employeeService = employeeService;
		this.linkedinService = linkedinService;
	}

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
	public ResponseEntity<List<Employee>> getEmployees(@RequestParam(value = "department", required = false) String department) {
		List<Employee> employees = employeeService.getEmployees(department);

		return Optional.of(employees)
                .map(u -> new ResponseEntity<List<Employee>>(u, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	/**
	 * Returns an Employee for the provided Employee Id
	 * @param employeeId
	 * @return
	 */
	@ApiOperation(value="Get Employee", notes = "Returns an Employee for the provided Employee Id",
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

	/**
	 * Creates an Employee
	 * @param employee
	 * @return
	 */
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

	/**
	 * Updates an Employee
	 * @param employeeId
	 * @param employee
	 * @return
	 */
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

	/**
	 * Deletes an Employee
	 * @param employeeId
	 * @return
	 */
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

	/**
	 * Returns Chandaraprakash's Linkedin profile data that includes the
	 * number of Connections and the profile image url
	 * @return
	 */
	@ApiOperation(value="Get Linkedin Profile Data", notes = "Returns Chandaraprakash's Linkedin profile data"
			+ "that includes the number of Connections and the profile image url",
			produces = "application/json",
			response = Profile.class, responseContainer = "Profile")
	@RequestMapping(value = "/v1/employees/linkedin",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Profile> getLinkedProfileData() {
		Profile profile = linkedinService.getBasicProfileData();

		return Optional.of(profile)
                .map(u -> new ResponseEntity<Profile>(u, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
}
