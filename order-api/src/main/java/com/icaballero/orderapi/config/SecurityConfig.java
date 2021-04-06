package com.icaballero.orderapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import com.icaballero.orderapi.security.RestAuthenticationEntryPoint;
import com.icaballero.orderapi.security.TokenAuthenticationFilter;
import com.icaballero.orderapi.utils.PathVariables;

/**
 * Clase para las configuraciones de seguridad
 * @author Ismael Caballero
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Bean
	public TokenAuthenticationFilter createTokenAuthenticationFilter() {
		
		return new TokenAuthenticationFilter();
	}
	
	@Bean
	public PasswordEncoder createPasswordEnconder() {
		return new BCryptPasswordEncoder();
	}
	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http.cors()
		 	.and()
		 	.sessionManagement()
		 	.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		 	.and()
		 .csrf()
		 	.disable()
		 .formLogin()
		 	.disable()
		 .httpBasic()
		 	.disable()
		 .exceptionHandling()
		 	.authenticationEntryPoint(new RestAuthenticationEntryPoint())
		 	.and()
		 .authorizeRequests()
		 	.antMatchers("/",
		 			"/error",
		 			"/favicon.ico",
		 			"/**/*.png",
		 			"/**/*.gif",
		 			"/**/*.svg",
		 			"/**/*.jpg",
		 			"/**/*.html",
		 			"/**/*.css",
		 			"/**/*.js",
		 			"/**/*.woff2")
		 	.permitAll()
		 	.antMatchers(PathVariables.LOGIN,
		 			PathVariables.SINGUP,
		 			PathVariables.SWAGGER_API_DOCS,
		 			PathVariables.SWAGGER_WEBJARS,
		 			PathVariables.SWAGGER_RESOURCES)
		 		.permitAll()
		 	.anyRequest()
		 		.authenticated();
		 
		 
		 //Añadir el filtro
		 http
		 .addFilterBefore(createTokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		 	
	}
	

}
