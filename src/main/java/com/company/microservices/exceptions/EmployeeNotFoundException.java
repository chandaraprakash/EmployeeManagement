package com.company.microservices.exceptions;

public class EmployeeNotFoundException extends RuntimeException {
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private String errorCode;
    private String errorMessage;

    public EmployeeNotFoundException(String errorCode, String errorMessage) {
    	this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode(){
    	return this.errorCode;
    }

    public String getErrorMessage(){
    	return this.errorMessage;
    }

}
