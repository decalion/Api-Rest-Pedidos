package com.icaballero.orderapi.dtos;

import lombok.Data;

@Data
public class LoginResponseDTO {
	
	private UserDTO user;
	private String token;

}
