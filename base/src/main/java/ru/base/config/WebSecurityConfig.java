package ru.base.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	
	private final UserDetailsService userDetailsService;

	@Autowired
	public WebSecurityConfig(@Qualifier("userService") UserDetailsService userDetailsService) {
			this.userDetailsService = userDetailsService;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeRequests()
			.antMatchers(HttpMethod.GET, "/index.html").permitAll()
			.antMatchers(HttpMethod.GET, "/*.js").permitAll()
			.antMatchers(HttpMethod.GET, "/*.gif").permitAll()
			.antMatchers(HttpMethod.GET, "/*.png").permitAll()
			.antMatchers(HttpMethod.GET, "/*.ico").permitAll()
			.antMatchers(HttpMethod.GET, "/*.css").permitAll()
			.antMatchers(HttpMethod.GET, "/assets/**").permitAll()
			.antMatchers(HttpMethod.GET, "/error").permitAll()
			.antMatchers(HttpMethod.POST, "/login/**").permitAll()
			.antMatchers(HttpMethod.GET, "/login/**").permitAll()
			.antMatchers(HttpMethod.POST, "/user/whoami/**").access("hasAnyRole('USER','ADMIN')")
			.antMatchers(HttpMethod.POST, "/rest/profile/**").access("hasAnyRole('USER','ADMIN')")
			.antMatchers(HttpMethod.PUT, "/rest/profile/**").access("hasAnyRole('USER','ADMIN')")
			.antMatchers(HttpMethod.PATCH, "/rest/profile/**").access("hasAnyRole('USER','ADMIN')")
			.antMatchers(HttpMethod.DELETE, "/rest/profile/**").access("hasAnyRole('USER','ADMIN')")
			.antMatchers(HttpMethod.GET, "/**").access("hasAnyRole('USER','ADMIN')")
			.antMatchers(HttpMethod.POST, "/**").access("hasRole('ADMIN')")
			.antMatchers(HttpMethod.PUT, "/**").access("hasRole('ADMIN')")
			.antMatchers(HttpMethod.PATCH, "/**").access("hasRole('ADMIN')")
			.antMatchers(HttpMethod.DELETE, "/**").access("hasRole('ADMIN')")
			.anyRequest()
			.authenticated()
		.and()
		.formLogin()
			.loginPage("/index.html")
			.loginProcessingUrl("/login")
			.defaultSuccessUrl("/index.html").permitAll()
		.and()
			.logout()
			.invalidateHttpSession(true)
			.clearAuthentication(true)
			.deleteCookies("JSESSIONID")
			.logoutSuccessUrl("/index.html").permitAll();


	} 

/*
.loginPage("/index.html").loginProcessingUrl("/auth/login").permitAll()

.loginPage("/auth/login").defaultSuccessUrl("/index.html").and()
	.logout().logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout","POST"))
	.invalidateHttpSession(true)
	.clearAuthentication(true)
	.deleteCookies("JSESSIONID")
	.logoutSuccessUrl("/auth/login").permitAll();
 */


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(daoAuthenticationProvider());		
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
		//return new BCryptPasswordEncoder(12);
	}

	@Bean
	protected DaoAuthenticationProvider daoAuthenticationProvider () {
		DaoAuthenticationProvider daoAuthenticationProvider =  new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		daoAuthenticationProvider.setUserDetailsService(userDetailsService);
		return daoAuthenticationProvider;
	}  
}
