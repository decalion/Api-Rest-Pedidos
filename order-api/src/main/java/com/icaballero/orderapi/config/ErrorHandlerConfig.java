package com.icaballero.orderapi.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.icaballero.orderapi.exceptions.GeneralServiceException;
import com.icaballero.orderapi.exceptions.NoDataFoundException;
import com.icaballero.orderapi.exceptions.ValidateServiceException;
import com.icaballero.orderapi.utils.WrapperResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * Clase para controlar las excepciones
 * @author Ismael Caballero
 *
 */
@ControllerAdvice
@Slf4j
public class ErrorHandlerConfig extends ResponseEntityExceptionHandler {
	
	
	/**
	 * 
	 * @param e
	 * @param request
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> all(Exception e, WebRequest request) {
		
		log.error(e.getMessage(),e);
		
		WrapperResponse<?> response = new WrapperResponse<>(false,"Internal Server Error",null);
		
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	/**
	 * 
	 * @param e
	 * @param request
	 * @return
	 */
	@ExceptionHandler(ValidateServiceException.class)
	public ResponseEntity<?> validateServiceException(ValidateServiceException e, WebRequest request) {
		log.info(e.getMessage(),e);
		WrapperResponse<?> response = new WrapperResponse<>(false,e.getMessage(),null);
		
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		
	}
	
	/**
	 * 
	 * @param e
	 * @param request
	 * @return
	 */
	@ExceptionHandler(NoDataFoundException.class)
	public ResponseEntity<?> noDataFoundException(NoDataFoundException e, WebRequest request) {
		log.info(e.getMessage(),e);
		WrapperResponse<?> response = new WrapperResponse<>(false,e.getMessage(),null);
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		
	}
	
	/**
	 * 
	 * @param e
	 * @param request
	 * @return
	 */
	@ExceptionHandler(GeneralServiceException.class)
	public ResponseEntity<?> generalServiceException(GeneralServiceException e, WebRequest request) {
		log.error(e.getMessage(),e);
		
		WrapperResponse<?> response = new WrapperResponse<>(false,"Internal Server Error",null);
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

}
