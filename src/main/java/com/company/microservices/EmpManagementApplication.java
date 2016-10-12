package com.company.microservices;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.company.microservices.web.interceptor.AuthorizationInterceptor;

@SpringBootApplication
public class EmpManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmpManagementApplication.class, args);
	}

	@Bean
	public AuthorizationInterceptor empAuthorizationInterceptor() {
		AuthorizationInterceptor authorizationInterceptor = new AuthorizationInterceptor();
		return authorizationInterceptor;
	}

	@Bean
	public WebMvcConfigurerAdapter adapter() {

		return new WebMvcConfigurerAdapter() {
			@Override
			public void addInterceptors(InterceptorRegistry registry) {

				try {
					// adding interceptor
					//registry.addInterceptor(empAuthorizationInterceptor());
					//registry.addInterceptor(empAuthorizationInterceptor())
							//.addPathPatterns("/employees/**");

				} catch (Exception e) {
					LoggerFactory.getLogger(this.getClass()).error(
							"Authorization failed", this);
				}
				super.addInterceptors(registry);
			}
		};
	}
}
