package com.icaballero.orderapi.dtos;

import lombok.Data;

@Data
public class ProductDTO {

	private Long id;
	private String name;
	private Double price;
}
