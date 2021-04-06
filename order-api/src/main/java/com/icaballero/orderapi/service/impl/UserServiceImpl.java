package com.icaballero.orderapi.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.icaballero.orderapi.converters.UserConverter;
import com.icaballero.orderapi.dtos.LoginRequestDTO;
import com.icaballero.orderapi.dtos.LoginResponseDTO;
import com.icaballero.orderapi.entity.User;
import com.icaballero.orderapi.exceptions.GeneralServiceException;
import com.icaballero.orderapi.exceptions.NoDataFoundException;
import com.icaballero.orderapi.exceptions.ValidateServiceException;
import com.icaballero.orderapi.repository.UserRepository;
import com.icaballero.orderapi.service.IUserService;
import com.icaballero.orderapi.validators.UserValidator;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements IUserService {

	@Value("${jwt.password}")
	private String jwtSecret;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private UserConverter converter;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	
	/**
	 * 
	 *{@inheritDoc}
	 *
	 */
	@Override
	public User createUser(User user) {

		try {

			UserValidator.singup(user);

			User exitsUser = userRepo.findByUsername(user.getUsername()).orElse(null);

			if (exitsUser != null)
				throw new ValidateServiceException("El nombre de usuario ya existe");

			//Encriptar el password
			String encoder = passwordEncoder.encode(user.getPassword());
			user.setPassword(encoder);
			
			return userRepo.save(user);

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
	 * 
	 *{@inheritDoc}
	 *
	 */
	@Override
	public LoginResponseDTO login(LoginRequestDTO request) {

		try {
			User user = userRepo.findByUsername(request.getUsername())
					.orElseThrow(() -> new ValidateServiceException("Usuario o password incorrectos"));

			if(!passwordEncoder.matches(request.getPassword(), user.getPassword()))
				throw new ValidateServiceException("Usuario o password incorrectos");

			String token = createToken(user);

			LoginResponseDTO dto = new LoginResponseDTO();
			dto.setUser(converter.fromEntity(user));
			dto.setToken(token);

			return dto;

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
	 * 
	 *{@inheritDoc}
	 *
	 */
	@Override
	public String createToken(User user) {
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + (1000 * 60 * 60)); // 1 Hora de duracion

		return Jwts.builder().setSubject(user.getUsername()).setIssuedAt(now).setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();

	}

	/**
	 * 
	 *{@inheritDoc}
	 *
	 */
	@Override
	public boolean validateToken(String token) {

		try {

		    Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
		    
		    return true;

		} catch (UnsupportedJwtException e) {
			log.error("JWT in a particular format/configuration that does not match the format expected");

		} catch (MalformedJwtException e) {
			log.error("JWT was not correctly constructed and should be rejected.");

		} catch (SignatureException e) {
			log.error("signature or verifying an existing signature of a JWT failed.");

		} catch (ExpiredJwtException e) {
			log.error("JWT was accepted after it expired and must be rejected.");

		} catch (Exception e) {
			log.error("Error JWT");
		}

		return false;

	}
	
	/**
	 * 
	 *{@inheritDoc}
	 *
	 */
	@Override
	public String getUsernameFromToken(String jwt) {
		try {
			return Jwts.parser().setSigningKey(jwtSecret)
					.parseClaimsJws(jwt)
					.getBody()
					.getSubject();	
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ValidateServiceException("Invalid Token");
		}
		
	}

}
