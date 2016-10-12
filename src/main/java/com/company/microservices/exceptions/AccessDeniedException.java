package com.company.microservices.exceptions;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AccessDeniedException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

    private static final String MESSAGE = "AccessDenied due to Authorization failure:: %s";

    public AccessDeniedException(String message) {
        super(String.format(MESSAGE, message));
        LoggerFactory.getLogger(this.getClass()).error(message, this);
    }

    public HttpStatus getHttpStatus() {
        return HttpStatus.UNAUTHORIZED;
    }

}
