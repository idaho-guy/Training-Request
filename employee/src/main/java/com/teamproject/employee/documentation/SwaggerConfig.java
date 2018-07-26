package com.teamproject.employee.documentation;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.HashSet;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public final HashSet<String> PRODUCES_AND_CONSUME = new HashSet<>(Arrays.asList("application/json", "application/xml"));

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfo("Employee API", "Access employee data",
                        "1.0", "", "teamproject", "", ""))
                .produces(PRODUCES_AND_CONSUME)
                .consumes(PRODUCES_AND_CONSUME);
    }
	/*
		http://localhost:8080/v2/api-docs
		http://localhost:8080/swagger-ui.html
	 */
}
