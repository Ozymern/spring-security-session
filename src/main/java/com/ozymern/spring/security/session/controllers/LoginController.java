package com.ozymern.spring.security.session.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

	private static final String LOGIN_VIEW = "login";
	private final Log logger = LogFactory.getLog(this.getClass());

	@GetMapping("/login")
	public String login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, Model model, Principal principal,
			RedirectAttributes flash) {

		// valida si el usuario esta autenticado
		if (principal != null) {
			logger.info("usuario esta autenticado redirect al home");

			return "redirect:/";
		}
		if (error != null) {
			model.addAttribute("error", "Nombre de usuario o contraseña incorrecta, vuelva a intentarlo");
			logger.info("Nombre de usuario o contraseña incorrecta, vuelva a intentarlo");
		}
		if (logout != null) {
			logger.info("usuario logout  con exito");

		}

		return LOGIN_VIEW;
	}

	@GetMapping("/logout")
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}

}
