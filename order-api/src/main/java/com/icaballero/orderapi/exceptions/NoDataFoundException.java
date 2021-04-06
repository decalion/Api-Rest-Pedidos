package com.icaballero.orderapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * Excepcion cuando no encuentra un registro
 * @author Ismael Caballero
 *
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NoDataFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4482355257537886728L;

	public NoDataFoundException() {
		super();

	}

	public NoDataFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

	public NoDataFoundException(String message, Throwable cause) {
		super(message, cause);
	
	}

	public NoDataFoundException(String message) {
		super(message);

	}

	public NoDataFoundException(Throwable cause) {
		super(cause);
		
	}
	
	

}
