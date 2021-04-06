package com.icaballero.orderapi.dtos;

import lombok.Data;

@Data
public class LoginRequestDTO {
	
	private String username;
	private String password;

}
