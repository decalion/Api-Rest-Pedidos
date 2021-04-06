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

import com.icaballero.orderapi.converters.ProductConverter;
import com.icaballero.orderapi.dtos.ProductDTO;
import com.icaballero.orderapi.entity.Product;
import com.icaballero.orderapi.service.IProductService;
import com.icaballero.orderapi.utils.Constants;
import com.icaballero.orderapi.utils.PathVariables;
import com.icaballero.orderapi.utils.WrapperResponse;

@RestController
public class ProductController {

	@Autowired
	private IProductService productService;

	@Autowired
	private ProductConverter converter;

	@GetMapping(value = PathVariables.PRODUCTS , consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<WrapperResponse<List<ProductDTO>>> findAll(
			@RequestParam(value = Constants.PAGE_NUMBER, required = false, defaultValue = Constants.NUMBER_0) int pageNumber,
			@RequestParam(value = Constants.PAGE_SIZE, required = false, defaultValue = Constants.NUMBER_5) int pageSize) {

		Pageable page = PageRequest.of(pageNumber, pageSize);

		List<Product> products = productService.findAll(page);

		List<ProductDTO> dtoProducts = converter.fromEntity(products);

		return new WrapperResponse<List<ProductDTO>>(true, Constants.SUCCESS, dtoProducts).createResponse();
	}

	@GetMapping(value = PathVariables.PRODUCTS_ID , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<WrapperResponse<ProductDTO>> findById(@PathVariable(Constants.ID) Long productId) {

		Product product = productService.findById(productId);

		ProductDTO productDTO = converter.fromEntity(product);

		return new WrapperResponse<ProductDTO>(true, Constants.SUCCESS, productDTO).createResponse();
	}

	@PostMapping(value = PathVariables.PRODUCTS , consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<WrapperResponse<ProductDTO>> create(@RequestBody ProductDTO product) {
		Product newProduct = productService.save(converter.fromDTO(product));

		ProductDTO productDTO = converter.fromEntity(newProduct);

		return new WrapperResponse<ProductDTO>(true, Constants.SUCCESS, productDTO).createResponse(HttpStatus.CREATED);
	}

	@PutMapping(value = PathVariables.PRODUCTS , consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<WrapperResponse<ProductDTO>> update(@RequestBody ProductDTO product) {

		Product updateProduct = productService.save(converter.fromDTO(product));

		ProductDTO productDTO = converter.fromEntity(updateProduct);

		return new WrapperResponse<ProductDTO>(true, Constants.SUCCESS, productDTO).createResponse();
	}

	@DeleteMapping(value = PathVariables.PRODUCTS_ID, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> delete(@PathVariable(Constants.ID) Long id) {

		productService.delete(id);

		return new WrapperResponse<>(true, Constants.SUCCESS, null).createResponse();
	}

}
