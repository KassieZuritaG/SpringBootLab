package com.velasco.ejercicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.velasco.ejercicio.models.services.UsuarioService;
import com.velasco.ejercicio.security.LoginSuccessHandler;

@Configuration
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UsuarioService service;
	
	@Autowired
	private LoginSuccessHandler handler;
		
	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
		
	@Autowired //Authetication
	public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception
	{	
		build.userDetailsService(service).passwordEncoder(encoder());	
	}
	
	@Override //Autorization
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/","/css/**","/fonts/**","/img/**","/js/**","/scss/**","/vendor/**").permitAll()
			.antMatchers("/usuario/**").anonymous()
			.antMatchers("/area/**").anonymous()
			.antMatchers("/photos/area**").anonymous()
			.antMatchers("/materia/**").hasAnyRole("ADMIN")
			.antMatchers("/alumno/**").hasAnyRole("USER")
			.antMatchers("/profesor/**").hasAnyRole("USER")
      .antMatchers("/photos/profesores/**").hasAnyRole("USER")
			.antMatchers("/photos/becas/*").hasAnyRole("ADMIN")
			.antMatchers("/h2-console/**").hasAnyRole("ADMIN")
			.anyRequest().authenticated()
			.and().formLogin().successHandler(handler).loginPage("/login").permitAll()			
			.and().logout().permitAll()			
			.and().exceptionHandling().accessDeniedPage("/error_403")
			.and()
				.csrf().ignoringAntMatchers("/h2-console/**")
			.and()
				.headers().frameOptions().sameOrigin();
	}
	
	
	
	
	
}
