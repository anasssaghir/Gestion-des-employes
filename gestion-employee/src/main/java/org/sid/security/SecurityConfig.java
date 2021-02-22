package org.sid.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		PasswordEncoder passwordEncoder= PasswordEncoder();
		auth.inMemoryAuthentication().withUser("ayman").password(passwordEncoder.encode("saghir")).roles("USER");
		auth.inMemoryAuthentication().withUser("youness").password(passwordEncoder.encode("saghir")).roles("USER");
		auth.inMemoryAuthentication().withUser("anass").password(passwordEncoder.encode("saghir")).roles("USER","ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin();
		http.authorizeRequests().antMatchers("/save**/**","/delete**/**","/form**/**").hasRole("ADMIN");
		http.authorizeRequests().antMatchers("/index**/**").hasRole("USER");
		//http.authorizeRequests().anyRequest().authenticated();
		http.csrf();
		http.exceptionHandling().accessDeniedPage("/notAuthorized");
	}
	
	@Bean
	public  PasswordEncoder PasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}