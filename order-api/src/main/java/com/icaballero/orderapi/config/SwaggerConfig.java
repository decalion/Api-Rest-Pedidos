package com.icaballero.orderapi.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.icaballero.orderapi.utils.Constants;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Clase de configuracion de Swagger
 * @author Ismael Caballero
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket apiDock() {
		
				return new Docket(DocumentationType.SWAGGER_2)
						.globalOperationParameters(Arrays.asList(
								new ParameterBuilder().
									name(Constants.AUTHORIZATION)
									.description("Token de autenticación").modelRef(new ModelRef("string"))
									.parameterType("header")
									.required(false)
									.build()
								))
						.select()
						.apis(RequestHandlerSelectors.basePackage("com.icaballero.orderapi.controllers"))
						.paths(PathSelectors.any())
						.build()
						.apiInfo(getApiInfo());
		
	}
	

	private ApiInfo getApiInfo() {
		
		return new ApiInfo("Order Service Api", 
				"Order Service api Description", 
				"1.0", 
				"http://icaballero.com", 
				new Contact("icaballero","http://icaballero.com", "apis@icaballero.com"), 
				"LICENSE", 
				"LICENSE URL", 
				Collections.emptyList());
	}
}
