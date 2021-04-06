package com.icaballero.orderapi.validators;

import com.icaballero.orderapi.entity.Product;
import com.icaballero.orderapi.exceptions.ValidateServiceException;


/**
 * Validador de productos
 * @author Ismael Caballero
 *
 */
public class ProductValidator {

	/**
	 * Validador de productos
	 * 
	 * @param product
	 */
	public static void save(Product product) {
		if (product.getName() == null || product.getName().trim().isEmpty()) {

			throw new ValidateServiceException("El nombre es requerido");
		}

		if (product.getName().length() > 100) {
			throw new ValidateServiceException("El nombre es muy largo (max 100)");
		}

		if (product.getPrice() == null) {
			throw new ValidateServiceException("El precio es requerido");
		}
		
		if(product.getPrice() <0) {
			throw new ValidateServiceException("El precio es incorrecto");
		}
	}
	
	
	

}
