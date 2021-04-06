package com.icaballero.orderapi.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.icaballero.orderapi.entity.Order;

public interface IOrderService {
	
	/**
	 * Buscar todas las ordenes
	 * @param page
	 * @return
	 */
	public List<Order> findAll(Pageable page);
	
	/**
	 * Buscar una orden
	 * @param id
	 * @return
	 */
	public Order findById(Long id);
	
	/**
	 * Borrar ordenes
	 * @param id
	 */
	@Transactional
	public void delete(Long id);
	
	
	/**
	 * 
	 * @param order
	 * @return
	 */
	@Transactional
	public Order save(Order order);

}
