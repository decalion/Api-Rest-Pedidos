package com.icaballero.orderapi.service;

import com.icaballero.orderapi.dtos.LoginRequestDTO;
import com.icaballero.orderapi.dtos.LoginResponseDTO;
import com.icaballero.orderapi.entity.User;

public interface IUserService {
	
	
	/**
	 * Funcion para dar de alta un usuario
	 * @param user
	 * @return
	 */
	public User createUser(User user);
	
	/**
	 * Funcion para hacer login
	 * @param request
	 * @return
	 */
	public LoginResponseDTO login(LoginRequestDTO request);
	
	
	/**
	 * Generar el JWT
	 * 
	 * @param user
	 * @return
	 */
	public String createToken(User user);
	
	
	/**
	 * Validar token
	 * @param token
	 * @return
	 */
	public boolean validateToken(String token) ;
	
	
	/**
	 * Devuelve el username del JWT
	 * @param jwt
	 * @return
	 */
	public String getUsernameFromToken(String jwt);

}
