package com.codeAnkit.blog.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.codeAnkit.blog.security.JwtAuthenticationEntryPoint;
import com.codeAnkit.blog.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableWebMvc
public class SecurityConfig {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception 
	{

		http.csrf(csrf -> csrf.disable())
		.cors(cors -> cors.disable())
		.authorizeHttpRequests(auth ->{
			try {
				auth.requestMatchers("/test")
				.authenticated()
				.requestMatchers("/api/v1/auth/login").permitAll()
				.anyRequest().authenticated()
				
				.exceptionHandling(ex ->ex.authenticationEntryPoint(jwtAuthenticationEntryPoint))
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
			} catch (Exception e) {
				
				//e.printStackTrace();
				System.out.println("envalid password and user");
			}
		});
		
        
         http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
         return http.build();
	}
	

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder);

		return provider;
	}

}
