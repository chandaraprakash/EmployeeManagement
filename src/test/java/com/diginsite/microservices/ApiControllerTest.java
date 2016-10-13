package com.diginsite.microservices;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.any;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.company.microservices.data.model.Employee;
import com.company.microservices.service.EmployeeService;
import com.company.microservices.service.LinkedinService;
import com.company.microservices.web.ApiController;

@RunWith(PowerMockRunner.class)
public class ApiControllerTest {

	@Mock
	public ApiController apiController;

	@Mock
	public EmployeeService employeeService;

	@Mock
	public LinkedinService linkedinService;

	@Before
    public void setup() {
        this.employeeService = Mockito.mock(EmployeeService.class);
        this.linkedinService = Mockito.mock(LinkedinService.class);
        this.apiController = new ApiController(employeeService, linkedinService);
    }

	@Test
	public void testGetEmployees() {
		Mockito.when(employeeService.getEmployees(anyString())).thenReturn(ApiControllerTestDataFactory.getEmployeesMockData());

		ResponseEntity<List<Employee>> response = this.apiController.getEmployees(anyString());

		assertThat(response.getBody().size(), equalTo(2));
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void testGetEmployee() {
		Mockito.when(employeeService.getEmployee(anyString())).thenReturn(ApiControllerTestDataFactory.getEmployeesMockData().get(0));

		ResponseEntity<Employee> response = this.apiController.getEmployee(anyString());

		assertNotNull(response.getBody());
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void testCreateEmployee() {
		Mockito.when(employeeService.createEmployee(any(Employee.class))).thenReturn(ApiControllerTestDataFactory.getEmployeesMockData().get(0));

		ResponseEntity<Employee> response = this.apiController.createEmployee(ApiControllerTestDataFactory.getEmployeePayloadMockData());

		assertNotNull(response.getBody());
		assertEquals(response.getStatusCode(), HttpStatus.CREATED);

	}

	@Test
	public void testUpdateEmployee() {
		Mockito.when(employeeService.updateEmployee(anyString(), any(Employee.class))).thenReturn(ApiControllerTestDataFactory.getEmployeesMockData().get(0));

		ResponseEntity<Employee> response = this.apiController.updateEmployee("EMP_ID", ApiControllerTestDataFactory.getEmployeePayloadMockData());

		assertNotNull(response.getBody());
		assertEquals(response.getStatusCode(), HttpStatus.OK);

	}

	@Test
	public void testDeleteEmployee() {
		Mockito.when(employeeService.deleteEmployee(anyString())).thenReturn(ApiControllerTestDataFactory.getEmployeesMockData().get(0));

		ResponseEntity<Employee> response = this.apiController.deleteEmployee("EMP_ID");

		assertNotNull(response.getBody());
		assertEquals(response.getStatusCode(), HttpStatus.NO_CONTENT);

	}

}
