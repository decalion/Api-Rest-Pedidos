package com.icaballero.orderapi.converters;

import com.icaballero.orderapi.dtos.ProductDTO;
import com.icaballero.orderapi.entity.Product;

/**
 * Convertidor de productos
 * 
 * @author Ismael Caballero
 *
 */
public class ProductConverter extends AbstractConverter<Product, ProductDTO> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ProductDTO fromEntity(Product entity) {
		if (entity == null)
			return null;

		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(entity.getId());
		productDTO.setName(entity.getName());
		productDTO.setPrice(entity.getPrice());

		return productDTO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Product fromDTO(ProductDTO dto) {
		if (dto == null)
			return null;
		
		Product product = new Product();
		product.setId(dto.getId());
		product.setName(dto.getName());
		product.setPrice(dto.getPrice());
		return product;
	}

}
