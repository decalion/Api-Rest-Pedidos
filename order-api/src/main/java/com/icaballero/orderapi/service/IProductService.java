package com.icaballero.orderapi.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.icaballero.orderapi.entity.Product;

/**
 * 
 * @author Ismael Caballero
 *
 */
public interface IProductService {
	
	/**
	 * Buscar producto por id
	 * 
	 * @param id del producto
	 * @return
	 */
	public Product findById(Long id);
	
	/**
	 * Eliminar un producto
	 * 
	 * @param id del producto
	 */
	@Transactional
	public void delete(Long id);
	
	/**
	 * Busca todos los productos
	 * 
	 * @return lista de productos
	 */
	public List<Product> findAll(Pageable page);
	
	
	/**
	 * 
	 * @param product
	 * @return
	 */
	@Transactional
	public Product save(Product product);
	
	

}
