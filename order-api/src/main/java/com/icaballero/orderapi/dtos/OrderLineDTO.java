package com.icaballero.orderapi.dtos;

import lombok.Data;

@Data
public class OrderLineDTO {
	
	private Long id;
	private ProductDTO product;
	private Double price;
	private Double quantity;
	private Double total;

}
