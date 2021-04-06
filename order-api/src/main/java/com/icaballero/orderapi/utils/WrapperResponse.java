package com.icaballero.orderapi.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WrapperResponse<T> {
	
	private boolean ok;
	private String message;
	private T body;
	

	public ResponseEntity<WrapperResponse<T>> createResponse(HttpStatus status) {
		
		return new ResponseEntity<WrapperResponse<T>>(this, status);
	}

	
	
	public ResponseEntity<WrapperResponse<T>> createResponse() {
		
		return new ResponseEntity<WrapperResponse<T>>(this, HttpStatus.OK);
	}

	
}
