package com.olx.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	UserDetailsService userDetailsService;

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().
		//antMatchers("/zenstock/**").hasAnyRole("ADMIN", "EMPLOYEE").
		antMatchers("/user/authenticate").hasRole("ADMIN").
		antMatchers("/employee").hasRole("EMPLOYEE").
		antMatchers("/testing").hasRole("ADMIN").and().formLogin();
		//antMatchers("/user/authenticate").permitAll().and().formLogin();
		
		/*
		 http.authorizeRequests()
		.antMatchers("/zenstock/**").hasAnyRole("ADMIN","EMPLOYEE")
		.antMatchers("/employee").hasRole("EMPLOYEE")
		.antMatchers("/admin").hasRole("ADMIN")
		.antMatchers("/all").permitAll().and().formLogin();
		 */
	}
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
		/*
		 * auth.inMemoryAuthentication()
		 * .withUser("shahabuddin").password(passwordEncoder.encode("ansari")).roles(
		 * "admin") .and()
		 * .withUser("nizam").password(passwordEncoder.encode("khan")).roles("user");
		 */
	}
	@Bean
	public AuthenticationManager getAuthenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	
}
