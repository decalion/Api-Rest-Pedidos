package com.icaballero.orderapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.icaballero.orderapi.converters.OrderConverter;
import com.icaballero.orderapi.dtos.OrderDTO;
import com.icaballero.orderapi.entity.Order;
import com.icaballero.orderapi.service.IOrderService;
import com.icaballero.orderapi.utils.Constants;
import com.icaballero.orderapi.utils.PathVariables;
import com.icaballero.orderapi.utils.WrapperResponse;

@RestController
public class OrderController {

	@Autowired
	private OrderConverter converter;

	@Autowired
	private IOrderService orderService;

	@GetMapping(value = PathVariables.ORDERS , consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<WrapperResponse<List<OrderDTO>>> findAll(
			@RequestParam(value = Constants.PAGE_NUMBER, required = false, defaultValue = Constants.NUMBER_0) int pageNumber,
			@RequestParam(value = Constants.PAGE_SIZE, required = false, defaultValue = Constants.NUMBER_5) int pageSize) {

		Pageable page = PageRequest.of(pageNumber, pageSize);

		List<Order> orders = orderService.findAll(page);

		return new WrapperResponse<List<OrderDTO>>(true, Constants.SUCCESS, converter.fromEntity(orders))
				.createResponse();
	}

	@GetMapping(value = PathVariables.ORDERS_ID , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<WrapperResponse<OrderDTO>> findById(@PathVariable(name = Constants.ID) Long id) {

		Order order = orderService.findById(id);

		return new WrapperResponse<OrderDTO>(true, Constants.SUCCESS, converter.fromEntity(order)).createResponse();
	}

	@PostMapping(value = PathVariables.ORDERS , consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<WrapperResponse<OrderDTO>> create(@RequestBody OrderDTO order) {


		Order newOrder = orderService.save(converter.fromDTO(order));

		return new WrapperResponse<OrderDTO>(true, Constants.SUCCESS, converter.fromEntity(newOrder))
				.createResponse(HttpStatus.CREATED);
	}

	@PutMapping(value = PathVariables.ORDERS , consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<WrapperResponse<OrderDTO>> update(@RequestBody OrderDTO order) {

		converter.fromDTO(order);
		Order updateOrder = orderService.save(converter.fromDTO(order));

		return new WrapperResponse<OrderDTO>(true, Constants.SUCCESS, converter.fromEntity(updateOrder))
				.createResponse();
	}

	@DeleteMapping(value = PathVariables.ORDERS_ID , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> delete(@PathVariable(name = Constants.ID) Long id) {

		orderService.delete(id);
		
		return new WrapperResponse<>(true, Constants.SUCCESS, null).createResponse();
	}

}
