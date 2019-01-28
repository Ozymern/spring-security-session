package com.ozymern.spring.security.session.controllers;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	private static final String ADMIN_VIEW = "admin/admin";

	private static final String USER_VIEW = "user/user";

	private final Log logger = LogFactory.getLog(this.getClass());

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String showAdmin(Authentication authentication, HttpServletRequest request) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		isUserInRole(auth, request, "ADMIN");

		return ADMIN_VIEW;
	}

	@GetMapping("/user")
	@PreAuthorize("hasRole('ROLE_USER')")
	public String showUser(Authentication authentication, HttpServletRequest request) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		isUserInRole(auth, request, "USER");
		return USER_VIEW;
	}

	// Metodo para validar el role
	public void isUserInRole(Authentication auth, HttpServletRequest request, String role) {
		SecurityContextHolderAwareRequestWrapper securityContext = new SecurityContextHolderAwareRequestWrapper(request,
				"ROLE_");
		if (securityContext.isUserInRole(role)) {
			logger.info(
					"Hola usuario " + auth.getName() + " utilizando de forma SecurityContextHolderAwareRequestWrapper");

		} else {
			logger.info("Hola usuario " + auth.getName()
					+ " usted no tiene acceso utilizando de forma SecurityContextHolderAwareRequestWrapper");

		}
	}

}
