package fr.dta.formafond.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class RestAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

	public RestAuthenticationSuccessHandler() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest arg0, HttpServletResponse response, Authentication arg2)
			throws IOException, ServletException {
		response.setStatus(HttpServletResponse.SC_OK);
		
	}

}
