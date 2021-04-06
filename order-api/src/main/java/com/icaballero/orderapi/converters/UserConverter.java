package com.icaballero.orderapi.converters;

import com.icaballero.orderapi.dtos.SignupRequestDTO;
import com.icaballero.orderapi.dtos.UserDTO;
import com.icaballero.orderapi.entity.User;

/**
 * Convertidor de User
 * 
 * @author Ismael Caballero
 *
 */
public class UserConverter extends AbstractConverter<User, UserDTO> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserDTO fromEntity(User entity) {
		if (entity == null)
			return null;

		UserDTO dto = new UserDTO();
		dto.setId(entity.getId());
		dto.setUsername(entity.getUsername());

		return dto;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User fromDTO(UserDTO dto) {
		if (dto == null)
			return null;

		User entity = new User();
		entity.setId(dto.getId());
		entity.setUsername(dto.getUsername());

		return entity;
	}

	/**
	 * 
	 * @param dto
	 * @return
	 */
	public User singup(SignupRequestDTO dto) {

		if (dto == null)
			return null;

		User entity = new User();
		entity.setUsername(dto.getUsername());
		entity.setPassword(dto.getPassword());

		return entity;
	}

}
