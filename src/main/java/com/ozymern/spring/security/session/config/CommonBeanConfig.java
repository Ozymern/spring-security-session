package com.ozymern.spring.security.session.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CommonBeanConfig implements WebMvcConfigurer {

	// metodo sobreescrito que redireciona cuando no se tiene permiso para acceder a
	// un recurso
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/error_403").setViewName("/error_403");
	}

	@Bean
	public BCryptPasswordEncoder passwordEncode() {
		return new BCryptPasswordEncoder();
	}
}
