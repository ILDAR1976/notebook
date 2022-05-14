package ru.base.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authorization.AuthorityAuthorizationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import ru.base.model.Role;
import ru.base.service.UserDetailsServiceImpl;
import ru.base.web.user.security.JwtConfigurer;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsServiceImpl userDetailsService;
	
	private final JwtConfigurer jwtConfigurer;

	
 	public WebSecurityConfig(JwtConfigurer jwtConfigurer) {
		this.jwtConfigurer = jwtConfigurer;
	}

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.authorizeRequests()
			.antMatchers(HttpMethod.GET, "/**").permitAll()
			
			.antMatchers(HttpMethod.POST, "/api/auth/**").permitAll()
			.antMatchers(HttpMethod.POST, "/user/register/**").permitAll()
			.antMatchers(HttpMethod.POST, "/user/whoami/**").access("hasAnyRole('USER','ADMIN')")
			.antMatchers(HttpMethod.POST, "rest/admin/users/**").access("hasRole('ADMIN')")
			.antMatchers(HttpMethod.POST, "/rest/profile/**").access("hasAnyRole('USER','ADMIN')")

			.antMatchers(HttpMethod.PUT, "/rest/profile/**").access("hasAnyRole('USER','ADMIN')")
			.antMatchers(HttpMethod.PUT, "rest/admin/users/**").access("hasRole('ADMIN')")

			.antMatchers(HttpMethod.PATCH, "/rest/profile/**").access("hasAnyRole('USER','ADMIN')")
			.antMatchers(HttpMethod.PATCH, "rest/admin/**").access("hasRole('ADMIN')")
			
			.antMatchers(HttpMethod.DELETE, "/rest/profile/**").access("hasAnyRole('USER','ADMIN')")
			.antMatchers(HttpMethod.DELETE, "rest/admin/**").access("hasRole('ADMIN')") 
			.anyRequest()
			.authenticated()
		.and()
		.apply(jwtConfigurer);

	} 

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

}
