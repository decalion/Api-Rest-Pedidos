package com.icaballero.orderapi.config;

import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import com.icaballero.orderapi.converters.OrderConverter;
import com.icaballero.orderapi.converters.ProductConverter;
import com.icaballero.orderapi.converters.UserConverter;

/**
 * 
 * Configurar los beans de los converters
 * @author Ismael Caballero
 *
 */
@Configuration
public class ConverterConfig {
		
	@Value("${config.datetimeFormat}")
	private String dateTimeFormat;
	
	
	
	/**
	 * Bean de ProductConverter
	 * @return ProductConverter
	 */
	@Bean
	public ProductConverter getProductConverter() {
		
		return new ProductConverter();
	}
	
	/**
	 * Bean de OrderConverter
	 * @return OrderConverter
	 */
	@Bean
	public OrderConverter getOrderConverter() {
		
		DateTimeFormatter format = DateTimeFormatter.ofPattern(dateTimeFormat);
		
		return new OrderConverter(format,getProductConverter());
	}
	
	
	/**
	 * Bean de UserConverter
	 * @return UserConverter
	 */
	
	@Bean
	public UserConverter getUserConverter() {
		
		return new UserConverter();
	}

}
