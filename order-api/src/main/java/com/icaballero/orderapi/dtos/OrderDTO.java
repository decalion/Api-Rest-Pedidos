package com.icaballero.orderapi.dtos;


import java.util.List;



import lombok.Data;

@Data
public class OrderDTO {

	private Long id;
	private String regDate;
	private List<OrderLineDTO> lines;
	private Double total;

}
