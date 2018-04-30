package fr.dta.formafond.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class RestAccessDeniedHandler implements AccessDeniedHandler{

	public RestAccessDeniedHandler() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handle(HttpServletRequest arg0, HttpServletResponse response, AccessDeniedException arg2)
			throws IOException, ServletException {
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	}

}
