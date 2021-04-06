package com.icaballero.orderapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * Excepcion de la capa de validadores
 * @author Ismael Caballero
 *
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ValidateServiceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5677795525271766893L;

	public ValidateServiceException() {
		super();

	}

	public ValidateServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

	public ValidateServiceException(String message, Throwable cause) {
		super(message, cause);
	
	}

	public ValidateServiceException(String message) {
		super(message);
		
	}

	public ValidateServiceException(Throwable cause) {
		super(cause);
		
	}

}
