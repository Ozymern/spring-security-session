package com.ozymern.spring.security.session.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ozymern.spring.security.session.models.services.UserDetailsServiceImpl;
import com.ozymern.spring.security.session.security.LoginSuccesHandler;

//anotacion para habilitar la anotacione @secured (securedEnabled=true) y prePostEnabled=true  	@PreAuthorize
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@Configuration
class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private LoginSuccesHandler loginSuccesHandler;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	// para las autorizaciones
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// permitir a todos
		http.authorizeRequests()
				// paginas permitidas para visualizar sin estar autenticado
				// .antMatchers("/").permitAll()
				// ejemplo de paginas a proteger sin anotaciones
				/*
				 * .antMatchers("/user/**").hasAnyRole("USER")
				 * .antMatchers("/admin/**").hasAnyRole("ADMIN")
				 */
				.anyRequest().authenticated().and().formLogin().usernameParameter("email").loginPage("/login")
				.successHandler(loginSuccesHandler).permitAll().and().logout().logoutUrl("/logout").permitAll().and()
				.exceptionHandling().accessDeniedPage("/error_403");

	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/js/**", "/images/**");
	}

	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder auth) throws Exception {

		// CON JPA
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);

	}

}
