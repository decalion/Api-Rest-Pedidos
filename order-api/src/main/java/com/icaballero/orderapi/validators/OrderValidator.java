package com.icaballero.orderapi.validators;

import com.icaballero.orderapi.entity.Order;
import com.icaballero.orderapi.entity.OrderLine;
import com.icaballero.orderapi.exceptions.ValidateServiceException;


/**
 * Validador de Orders
 * @author Ismael Caballero
 *
 */
public class OrderValidator {
	
	/**
	 * Validar 
	 * @param order
	 */
	public static void save(Order order) {
		
//		if(order.getTotal() == null) {
//			throw new ValidateServiceException("El total es requerido");
//		}
//		
//		if(order.getLines() == null || order.getLines().isEmpty()) {
//			throw new ValidateServiceException("Las Lineas son requeridas");
//		}
		
		
		for (OrderLine line : order.getLines()) {
			
//			if(line.getPrice() == null) {
//				throw new ValidateServiceException("El precio es requerido");
//			}
//			
//			if(line.getPrice() < 0) {
//				throw new ValidateServiceException("El precio es incorrecto");
//			}
			
			if(line.getProduct() == null || line.getProduct().getId() == null) {
				throw new ValidateServiceException("El Producto es requerido");
			}
			
			if(line.getQuantity() == null) {
				throw new ValidateServiceException("La cantidad es requerido");
			}
			
			if(line.getQuantity() < 0) {
				throw new ValidateServiceException("La cantidad es incorrecto");
			}
			
//			if(line.getTotal() == null) {
//				throw new ValidateServiceException("El Total es requerido");
//			}
//			
//			if(line.getTotal()  < 0) {
//				throw new ValidateServiceException("El Total es incorrecto");
//			}
//			
			
		}
		
	}

}
