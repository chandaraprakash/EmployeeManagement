package com.company.microservices.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(annotations = RestController.class)
public class ApiControllerAdvice {

	@ExceptionHandler(EmployeeNotFoundException.class)
    ResponseEntity<Status> companyNotFoundException(HttpServletRequest req, EmployeeNotFoundException e) {
        Status status = new Status();
        status.setErrorCode(e.getErrorCode());
        status.setErrorMessage(e.getErrorMessage());
		return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
    }
}
