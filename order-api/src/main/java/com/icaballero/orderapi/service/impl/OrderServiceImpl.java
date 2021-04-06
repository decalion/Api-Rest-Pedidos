package com.icaballero.orderapi.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icaballero.orderapi.entity.Order;
import com.icaballero.orderapi.entity.OrderLine;
import com.icaballero.orderapi.entity.Product;
import com.icaballero.orderapi.entity.User;
import com.icaballero.orderapi.exceptions.GeneralServiceException;
import com.icaballero.orderapi.exceptions.NoDataFoundException;
import com.icaballero.orderapi.exceptions.ValidateServiceException;
import com.icaballero.orderapi.repository.OrderLineRepository;
import com.icaballero.orderapi.repository.OrderRepository;
import com.icaballero.orderapi.repository.ProductRepository;
import com.icaballero.orderapi.security.UserPrincipal;
import com.icaballero.orderapi.service.IOrderService;
import com.icaballero.orderapi.validators.OrderValidator;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderServiceImpl implements IOrderService {

	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private OrderLineRepository orderLineRepo;
	
	@Autowired
	private ProductRepository productRepo;

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Order> findAll(Pageable page) {

		try {
			
			return orderRepo.findAll(page).toList();

		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);

			throw e;
		}

		catch (Exception e) {
			log.error(e.getMessage(), e);

			throw new GeneralServiceException(e.getMessage(), e);

		}

	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Order findById(Long id) {
		
		try {
			
			return orderRepo.findById(id)
					.orElseThrow( () -> new NoDataFoundException("La Orden no existe"));
			

		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);

			throw e;
		}

		catch (Exception e) {
			log.error(e.getMessage(), e);

			throw new GeneralServiceException(e.getMessage(), e);

		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Transactional
	@Override
	public void delete(Long id) {
		
		try {
			Order order = orderRepo.findById(id)
					.orElseThrow( () -> new NoDataFoundException("La Orden no existe"));
			
			orderRepo.delete(order);
			
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);

			throw e;
		}

		catch (Exception e) {
			log.error(e.getMessage(), e);

			throw new GeneralServiceException(e.getMessage(), e);

		}
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Transactional
	@Override
	public Order save(Order order) {
		
		try {

			
			//Validaciones 
			OrderValidator.save(order);
			
			User user = UserPrincipal.getCurrentUser();
					
			
			//Validar que el producto exista en BBDD
			double total = 0;
			for(OrderLine line : order.getLines()) {
				Product product = productRepo.findById(line.getProduct().getId())
				.orElseThrow( ()  -> new NoDataFoundException("No existe el producto " + line.getProduct().getId()));

				//Calcular el total y los precios
				line.setTotal(product.getPrice());
				line.setTotal(product.getPrice() * line.getQuantity());
				total+= line.getTotal();
				
			}
			order.setTotal(total);
			
			order.getLines().forEach(line -> line.setOrder(order));
			
			if(order.getId() == null) {
				order.setUser(user);
				order.setRegDate(LocalDateTime.now());
				return orderRepo.save(order);
				
			}
			
			Order updateOrder = orderRepo.findById(order.getId())
					.orElseThrow( () -> new NoDataFoundException("La Orden no existe"));
			
			order.setRegDate(updateOrder.getRegDate());
			
			//Eliminar lineas no existentes en el update
			List<OrderLine> deleteLine = updateOrder.getLines();
			deleteLine.removeAll(order.getLines());
			orderLineRepo.deleteAll(deleteLine);
			
	
			
			return orderRepo.save(order);
			
			
			

		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);

			throw e;
		}

		catch (Exception e) {
			log.error(e.getMessage(), e);

			throw new GeneralServiceException(e.getMessage(), e);

		}
	}

}
