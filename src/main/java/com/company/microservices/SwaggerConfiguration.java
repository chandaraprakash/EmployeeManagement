package com.company.microservices;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@ComponentScan(basePackages = {"com.company.microservices"})
public class SwaggerConfiguration {
	static final String detailDescription = "The `Employee management Microservice` is a RESTful API that provides operations to access Employee Details. \n \n"
			+"Below is a list of available REST API calls.";

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
		.groupName("Employee")
        .apiInfo(apiInfo())
		.select()
        .apis(RequestHandlerSelectors.basePackage("com.company.microservices"))
        .paths(PathSelectors.any())
        .build()
        .useDefaultResponseMessages(false)
		.forCodeGeneration(true);
	}

	private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Employee Management")
                .description(detailDescription)
                .version("1.0")
                .build();
    }

}
