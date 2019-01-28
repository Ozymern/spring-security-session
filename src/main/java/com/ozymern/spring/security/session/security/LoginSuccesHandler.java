package com.ozymern.spring.security.session.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class LoginSuccesHandler extends SimpleUrlAuthenticationSuccessHandler {

	// metodo que se activa inmediatamente despues de que el usuario se ha
	// autenticado con exito
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		// SimpleUrlAuthenticationSuccessHandler hereda de
		// AbstractAuthenticationTargetUrlRequestHandler
		// la cual implementa= protected final Log logger =
		// LogFactory.getLog(this.getClass())
		if (authentication != null) {
			logger.info("usuario autenticado con exito");
		}

		super.onAuthenticationSuccess(request, response, authentication);
	}

}
