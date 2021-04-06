package com.icaballero.orderapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icaballero.orderapi.entity.Product;
import com.icaballero.orderapi.exceptions.GeneralServiceException;
import com.icaballero.orderapi.exceptions.NoDataFoundException;
import com.icaballero.orderapi.exceptions.ValidateServiceException;
import com.icaballero.orderapi.repository.OrderLineRepository;
import com.icaballero.orderapi.repository.ProductRepository;
import com.icaballero.orderapi.service.IProductService;
import com.icaballero.orderapi.validators.ProductValidator;

import lombok.extern.slf4j.Slf4j;

/**
 * Servicio Productos
 * 
 * @author Ismael Caballero
 *
 */
@Service
@Slf4j
public class ProductServiceImpl implements IProductService {

	@Autowired
	private ProductRepository productRepo;

	@Autowired
	private OrderLineRepository orderLineRepo;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Product findById(Long id) {

		try {

			Product product = productRepo.findById(id)
					.orElseThrow(() -> new NoDataFoundException("No existe el producto"));

			return product;

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

			Product product = productRepo.findById(id)
					.orElseThrow(() -> new NoDataFoundException("No existe el producto"));

			// List<OrderLine> orderLine = orderLineRepo.findByProduct(product.getId());

			// Eliminamos la relacion de productos
			orderLineRepo.deleteByProduct(product.getId());

			// Eliminamos los productos
			productRepo.delete(product);

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
	public List<Product> findAll(Pageable page) {

		try {

			List<Product> products = productRepo.findAll(page).toList();

			return products;

		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);

			throw e;
		}

		catch (Exception e) {
			log.error(e.getMessage(), e);

			throw new GeneralServiceException(e.getMessage(), e);

		}

	}

//	/**
//	 * Guarda un producto en la BBDD
//	 * 
//	 * @param product a guardar
//	 * @return el producto guardado
//	 */
//	@Transactional
//	public Product create(Product product) {
//		Product newProduct = productRepo.save(product);
//
//		return newProduct;
//	}
//
//	/**
//	 * Actualiza un producto en la BBDD
//	 * 
//	 * @param product a actualizar
//	 * @return el producto actualizado
//	 */
//	@Transactional
//	public Product update(Product product) {
//		Product exitsProduct = productRepo.findById(product.getId())
//				.orElseThrow(() -> new RuntimeException("No existe el producto"));
//
//		exitsProduct.setName(product.getName());
//		exitsProduct.setPrice(product.getPrice());
//
//		productRepo.save(exitsProduct);
//
//		return exitsProduct;
//	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional
	@Override
	public Product save(Product product) {

		try {

			ProductValidator.save(product);

			if (product.getId() == null) {
				Product newProduct = productRepo.save(product);

				return newProduct;

			}

			Product exitsProduct = productRepo.findById(product.getId())
					.orElseThrow(() -> new NoDataFoundException("No existe el producto"));

			exitsProduct.setName(product.getName());
			exitsProduct.setPrice(product.getPrice());

			productRepo.save(exitsProduct);

			return exitsProduct;

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
