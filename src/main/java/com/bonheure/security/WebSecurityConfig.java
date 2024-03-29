package com.bonheure.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
 
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// Disable CSRF (cross site request forgery)
		http.csrf().disable();

		// No session will be created or used by spring security
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

//		// Entry points
//		http.authorizeRequests()//
//				// .antMatchers("/**/signin").permitAll()//
//				//.antMatchers("/**/signup").permitAll()//
//				//.antMatchers("/users/**").hasAnyAuthority("CLIENT", "SUPERADMIN")//
//				//.antMatchers("/clients/**").hasAnyAuthority("CLIENT", "SUPERADMIN")//
//				.antMatchers("/prestataires/**").hasAnyAuthority("PRESTATAIRE", "SUPERADMIN")//
//				.antMatchers("/groups/**").hasAuthority("SUPERADMIN")//
//				.antMatchers("/categories/**").hasAuthority("SUPERADMIN")//
//				.antMatchers("/prestations/**").hasAnyAuthority("PRESTATAIRE", "SUPERADMIN")//
//				.antMatchers("/companies/**").hasAuthority("SUPERADMIN")//
//				.antMatchers("/working areas/**").hasAnyAuthority("PRESTATAIRE", "SUPERADMIN")//
//				.antMatchers("/address/**").hasAnyAuthority("PRESTATAIRE", "SUPERADMIN", "CLIENT") 
//				.anyRequest().authenticated();
//.antMatchers(HttpMethod.DELETE).hasAnyAuthority("SUPERADMIN")//

		// If a user try to access a resource without   having enough permissions
		http.exceptionHandling().accessDeniedPage("/login");

		// Apply JWT
		http.apply(new JwtTokenFilterConfigurer(jwtTokenProvider));

		// Optional, if you want to test the API from a browser
		// http.httpBasic();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// Allow swagger to be accessed without authentication
		web.ignoring().antMatchers("/v2/api-docs")//
				.antMatchers("/swagger-resources/**")//
				.antMatchers("/swagger-ui.html")//
				.antMatchers("/configuration/**")//
				.antMatchers("/webjars/**")//
				.antMatchers("/public");

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12);
	}

}
