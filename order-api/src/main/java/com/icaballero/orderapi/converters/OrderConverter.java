package com.icaballero.orderapi.converters;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import com.icaballero.orderapi.dtos.OrderDTO;
import com.icaballero.orderapi.dtos.OrderLineDTO;
import com.icaballero.orderapi.entity.Order;
import com.icaballero.orderapi.entity.OrderLine;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OrderConverter extends AbstractConverter<Order, OrderDTO> {

	private  DateTimeFormatter dateTimeFortmat;
	private ProductConverter productConverter;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public OrderDTO fromEntity(Order entity) {

		if (entity == null)
			return null;

		List<OrderLineDTO> lines = fromOrderLineEntity(entity.getLines());

		OrderDTO dto = new OrderDTO();
		dto.setId(entity.getId());
		dto.setLines(lines);
		dto.setRegDate(entity.getRegDate().format(dateTimeFortmat));
		dto.setTotal(entity.getTotal());

		return dto;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Order fromDTO(OrderDTO dto) {

		if (dto == null)
			return null;

		List<OrderLine> lines = fromOrderLineDTO(dto.getLines());

		Order entity = new Order();
		entity.setId(dto.getId());
		entity.setLines(lines);
		entity.setTotal(dto.getTotal());

		return entity;
	}

	/**
	 * 
	 * @param line
	 * @return
	 */
	private List<OrderLineDTO> fromOrderLineEntity(List<OrderLine> lines) {

		if (lines == null)
			return null;

		return lines.stream().map(line -> {

			OrderLineDTO dto = new OrderLineDTO();
			dto.setId(line.getId());
			dto.setPrice(line.getPrice());
			dto.setProduct(productConverter.fromEntity(line.getProduct()));
			dto.setQuantity(line.getQuantity());
			dto.setTotal(line.getTotal());

			return dto;

		}).collect(Collectors.toList());

	}

	/**
	 * 
	 * @param line
	 * @return
	 */
	private List<OrderLine> fromOrderLineDTO(List<OrderLineDTO> lines) {
		if (lines == null)
			return null;

		return lines.stream().map(line -> {

			OrderLine entity = new OrderLine();
			entity.setId(line.getId());
			entity.setPrice(line.getPrice());
			entity.setProduct(productConverter.fromDTO(line.getProduct()));
			entity.setQuantity(line.getQuantity());
			entity.setTotal(line.getTotal());

			return entity;

		}).collect(Collectors.toList());

	}

}
