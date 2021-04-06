package com.icaballero.orderapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.icaballero.orderapi.converters.UserConverter;
import com.icaballero.orderapi.dtos.LoginRequestDTO;
import com.icaballero.orderapi.dtos.LoginResponseDTO;
import com.icaballero.orderapi.dtos.SignupRequestDTO;
import com.icaballero.orderapi.dtos.UserDTO;
import com.icaballero.orderapi.entity.User;
import com.icaballero.orderapi.service.IUserService;
import com.icaballero.orderapi.utils.Constants;
import com.icaballero.orderapi.utils.PathVariables;
import com.icaballero.orderapi.utils.WrapperResponse;

@RestController
public class UserController {
	
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private UserConverter converter;
	
	
	@PostMapping(value = PathVariables.SINGUP,consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<WrapperResponse<UserDTO>> signup(@RequestBody SignupRequestDTO request) {
		
		User user = userService.createUser(converter.singup(request));
		
		return new WrapperResponse<UserDTO>(true, Constants.SUCCESS, converter.fromEntity(user))
				.createResponse(HttpStatus.OK);
	}
	
	
	@PostMapping(value = PathVariables.LOGIN, consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<WrapperResponse<LoginResponseDTO>> login(@RequestBody LoginRequestDTO request) {
		
		LoginResponseDTO response = userService.login(request);
		
		return new WrapperResponse<LoginResponseDTO>(true, Constants.SUCCESS, response)
				.createResponse(HttpStatus.OK);
	}

}
