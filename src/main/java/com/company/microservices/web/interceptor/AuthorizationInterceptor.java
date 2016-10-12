package com.company.microservices.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.company.microservices.exceptions.AccessDeniedException;

@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String auth = request.getHeader("Authorization");
        if (null == auth || !"hrapp".equals(auth)) {
        	throw new AccessDeniedException("Invalid Authorization Header");
        }

		return true;
	}

}