package com.icaballero.orderapi.converters;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Convertidor abstracto
 * 
 * @author Ismael Caballero
 *
 * @param <E> Entidad a convertir
 * @param <D> DTO a convertir
 */
public abstract class AbstractConverter<E, D> {

	/**
	 * Convierte de Entity a DTO
	 * 
	 * @param entity
	 * @return DTO
	 */
	public abstract D fromEntity(E entity);

	/**
	 * Convierte de DTO a Entity
	 * 
	 * @param dto
	 * @return Entity
	 */
	public abstract E fromDTO(D dto);

	/**
	 * Convierte lista de Entitys a lista DTO
	 * 
	 * @param entitys
	 * @return
	 */
	public List<D> fromEntity(List<E> entitys) {

		if (entitys == null)
			return null;

		return entitys.stream().map(e -> fromEntity(e)).collect(Collectors.toList());
	}

	/**
	 * Convierte lista de DTO a lista Entitys
	 * 
	 * @param dtos
	 * @return
	 */
	public List<E> fromDTO(List<D> dtos) {
		
		if (dtos == null)
			return null;

		return dtos.stream().map(d -> fromDTO(d)).collect(Collectors.toList());
	}

}
